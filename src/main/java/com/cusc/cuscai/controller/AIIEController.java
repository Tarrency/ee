package com.cusc.cuscai.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cusc.cuscai.service.AgentService;
import com.cusc.cuscai.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Api(tags = "AI交互引擎相关接口")
@RestController
@RequestMapping("/aiie")
public class AIIEController {

    @Autowired
    private AgentService agentService;

    @ApiOperation("获取目前现有的模型")
    @PostMapping("/getModels")
    public Result getModels(@RequestParam("agentId") @ApiParam(value = "agentId", required = true) Integer agentId){
        if(!agentService.isAgentExist(agentId)){
            return Result.fail(41100,String.format("Agent : %d 不存在",agentId));
        }

        return Result.fail(500,"接口未完成");
    }

    /**
     * @param jsonParam：
     * modelIds	    String[]	模型id数组
     * modelType	int	        模型类型
     * userText	    String	    需要预测的文本
     * @return
     */
    @ApiOperation(value = "获取模型预测结果", notes = "根据模型类型和ID获取模型预测结果，ID可多个，即返回多个模型综合预测结果")
    @PostMapping(value = "/predict", produces = "application/json;charset=UTF-8")
    public Result preditc(@RequestBody @ApiParam("接口数据") JSONObject jsonParam){
        if (Objects.isNull(jsonParam)) {
            return Result.fail(400,"请求参数错误");
        }
        try {
            JSONArray modelIDs = jsonParam.getJSONArray("modelIds");
            Integer modelType = jsonParam.getInteger("modelType");
            String userText = jsonParam.getString("userText");

            if (Objects.isNull(modelIDs) || Objects.isNull(modelType) || Objects.isNull(userText)){
                return Result.fail(400,"请求参数错误");
            }

            System.out.println(modelIDs);
            System.out.println(modelType);
            System.out.println(userText);

        } catch (Exception e){
            e.printStackTrace();
            return Result.fail(500,"服务器出错");
        }
        return Result.fail(500,"接口未完成");
    }
}
