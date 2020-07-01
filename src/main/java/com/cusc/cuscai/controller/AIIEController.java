package com.cusc.cuscai.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
                        jsonArray.addAll(response.getJSONObject("retData").values());
                    }
                }

                return Result.success(21100, "获取现有的模型成功", jsonArray);
            } else {
                return Result.fail(41100, "获取现有的模型失败");
            }
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
                           @RequestParam("userText") @ApiParam(value = "userText", required = true) Integer userText){
        if (!agentService.isAgentExist(agentId)) {
            return Result.fail(41000, String.format("Agent : %d 不存在", agentId));
        }

        AgentModelBO agentModelBO = agentService.searchAgentModels(agentId);


        return Result.fail(500, "服务器出错");
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

            // TODO: 2020/6/30 敏感词过滤 
            
            if (modelType == 0) {
                JSONObject params = new JSONObject();
                params.put("modelId", modelIDs);
                params.put("userText", userText);
                JSONObject response = aiieService.postResponse(qaaiUrl, "/qaai/predict", params);
                if (response != null) {
                    return Result.success(21101,"模型预测成功",response.getJSONObject("retData"));
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
