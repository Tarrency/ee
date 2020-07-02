package com.cusc.cuscai.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cusc.cuscai.common.GlobalCache;
import com.cusc.cuscai.entity.apibo.AgentModelBO;
import com.cusc.cuscai.service.AIIEService;
import com.cusc.cuscai.service.AgentService;
import com.cusc.cuscai.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 业务平台交互引擎归入AI交互引擎
 */
@Api(tags = "AI交互引擎相关接口")
@RestController
@RequestMapping("/aiie")
public class AIIEController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private AIIEService aiieService;

    @Value("${remote-ai.qaai.url}")
    private String qaaiUrl;

    @Value("${remote-ai.kgai.url}")
    private String kgaiUrl;

    @ApiOperation("获取目前现有的模型")
    @PostMapping("/getModels")
    public Result getModels(@RequestParam("agentId") @ApiParam(value = "agentId", required = true) Integer agentId) {
        if (!agentService.isAgentExist(agentId)) {
            return Result.fail(41100, String.format("Agent : %d 不存在", agentId));
        }

        JSONArray jsonArray = new JSONArray();

        try {
            // 获取QA模型
            JSONObject response = aiieService.getResponse(qaaiUrl, "/qaai/getModels");
            if (response != null) {
                System.out.println(response);
                if (response.containsKey("retData")) {
                    JSONObject res = response.getJSONObject("retData");
                    if (res != null) {
                        jsonArray.addAll(res.values());
                    }
                }
            }

            // 获取KG模型
            response = aiieService.getResponse(kgaiUrl, "/kgai/getModels");
            if (response != null) {
                System.out.println(response);
                if (response.containsKey("retData")) {
                    JSONArray res = response.getJSONArray("retData");
                    if (res != null) {
                        jsonArray.addAll(res);
                    }
                }
            }

            return Result.success(21100, "获取现有的模型成功", jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(41100, "获取现有的模型失败");
        }

    }

    @ApiOperation(value = "获取模型预测结果（user用）", notes = "根据agentID返回模型综合预测结果")
    @PostMapping("/question")
    public Result question(@RequestParam("sessionId") @ApiParam(value = "sessionId", required = true) String sessionId,
                           @RequestParam("userId") @ApiParam(value = "userId", required = true) String userId,
                           @RequestParam("agentId") @ApiParam(value = "agentId", required = true) Integer agentId,
                           @RequestParam("userText") @ApiParam(value = "userText", required = true) String userText){
        if (!agentService.isAgentExist(agentId)) {
            return Result.fail(41000, String.format("Agent : %d 不存在", agentId));
        }

        //敏感词匹配
        if (GlobalCache.sensitiveFilter.filter(userText)){
            return Result.success(21000,"回答包含敏感词","您的提问包含敏感词");
        }

        AgentModelBO agentModelBO = agentService.searchAgentModels(agentId);
        try{
            if (agentModelBO.getModelType() == 0) {
                JSONObject params = new JSONObject();
                params.put("modelId", agentModelBO.getModelIds());
                params.put("userText", userText);
                JSONObject response = aiieService.postResponse(qaaiUrl, "/qaai/predict", params);
                if (response != null) {
                    return Result.success(21101,"模型预测成功",response.getJSONObject("retData"));
                } else {
                    return Result.fail(41201, "模型预测失败");
                }
            } else if(agentModelBO.getModelType() == 1){
                JSONObject params = new JSONObject();
                params.put("modelIds", agentModelBO.getModelIds());
                params.put("userText", userText);
                JSONObject response = aiieService.postResponse(kgaiUrl, "/kgai/predict", params);
                if (response != null && response.containsKey("retData")) {
                    return Result.success(21101,"模型预测成功",response.getJSONArray("retData"));
                } else {
                    return Result.fail(41201, "模型预测失败");
                }
            } else {
                return Result.fail(41201, "模型不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(500, "服务器出错");
        }
    }

    /**
     * @param jsonParam： modelIds	    String[]	模型id数组
     *                   modelType	int	        模型类型
     *                   userText	    String	    需要预测的文本
     * @return Result
     */
    @ApiOperation(value = "获取模型预测结果（agent测试用）", notes = "根据模型类型和ID获取模型预测结果，ID可多个，即返回多个模型综合预测结果")
    @PostMapping(value = "/predict", produces = "application/json;charset=UTF-8")
    public Result predict(@ApiParam("接口数据") @RequestBody JSONObject jsonParam) {
        if (Objects.isNull(jsonParam)) {
            return Result.fail(400, "请求参数错误");
        }
        try {
            JSONArray modelIDs = jsonParam.getJSONArray("modelIds");
            Integer modelType = jsonParam.getInteger("modelType");
            String userText = jsonParam.getString("userText");

            if (Objects.isNull(modelIDs) || Objects.isNull(modelType) || Objects.isNull(userText)) {
                return Result.fail(400, "请求参数错误");
            }

            //敏感词匹配
            if (GlobalCache.sensitiveFilter.filter(userText)){
                return Result.success(21101,"回答包含敏感词","您的提问包含敏感词");
            }
            
            if (modelType == 0) {
                JSONObject params = new JSONObject();
                params.put("modelId", modelIDs);
                params.put("userText", userText);
                JSONObject response = aiieService.postResponse(qaaiUrl, "/qaai/predict", params);
                if (response != null && response.getIntValue("retCode") == 21507) {
                    return Result.success(21101,"模型预测成功",response.getJSONObject("retData"));
                } else {
                    return Result.fail(41201, "模型预测失败");
                }
            } else if(modelType == 1){
                JSONObject params = new JSONObject();
                params.put("modelIds", modelIDs);
                params.put("userText", userText);
                JSONObject response = aiieService.postResponse(kgaiUrl, "/kgai/predict", params);
                if (response != null && response.containsKey("retData")) {
                    return Result.success(21101,"模型预测成功",response.getJSONArray("retData"));
                } else {
                    return Result.fail(41201, "模型预测失败");
                }
            } else {
                return Result.fail(41201, "模型不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(500, "服务器出错");
        }
    }
}
