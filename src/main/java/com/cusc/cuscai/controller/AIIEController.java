package com.cusc.cuscai.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cusc.cuscai.common.GlobalCache;
import com.cusc.cuscai.entity.apibo.AgentModelBO;
import com.cusc.cuscai.entity.bo.HistoryDialogueInfoBO;
import com.cusc.cuscai.entity.model.HistoryDialogueInfo;
import com.cusc.cuscai.entity.model.ModelFeedbackInfo;
import com.cusc.cuscai.service.AIIEService;
import com.cusc.cuscai.service.AgentService;
import com.cusc.cuscai.service.HistoryDialogueService;
import com.cusc.cuscai.service.ModelFeedbackService;
import com.cusc.cuscai.util.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 智能问答平台交互引擎
 * * 业务平台交互引擎归入智能问答平台交互引擎（AIIE）
 * <p>
 * 主要功能：
 * * 模型引擎交互接口
 * * 日志记录
 */
@Api(tags = "AI交互引擎相关接口")
@RestController
@RequestMapping("/aiie")
public class AIIEController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private AIIEService aiieService;

    @Autowired
    private HistoryDialogueService historyDialogueService;

    @Autowired
    private ModelFeedbackService modelFeedbackService;

    @Value("${remote-ai.qaai.url}")
    private String qaaiUrl;

    @Value("${remote-ai.kgai.url}")
    private String kgaiUrl;

    /////////////// User(app侧）调用接口 ///////////////

    /**
     *
     * 根据agentID返回模型综合预测结果
     *
     * @param sessionId sessionId
     * @param userId userId
     * @param agentId agentId
     * @param userText 用户输入的问题文本
     * @return Result
     */
    @ApiOperation(value = "获取模型预测结果（user用）", notes = "根据agentID返回模型综合预测结果")
    @PostMapping("/question")
    public Result question(@RequestParam("sessionId") @ApiParam(value = "sessionId", required = true) String sessionId,
                           @RequestParam("userId") @ApiParam(value = "userId", required = true) String userId,
                           @RequestParam("agentId") @ApiParam(value = "agentId", required = true) Integer agentId,
                           @RequestParam("userText") @ApiParam(value = "userText", required = true) String userText) {
        if (!agentService.isAgentExist(agentId)) {
            return Result.fail(40724, String.format("Agent : %d 不存在", agentId));
        }

        //敏感词匹配
        if (GlobalCache.sensitiveFilter.filter(userText)) {
            return Result.success(40724, "回答包含敏感词");
        }

        AgentModelBO agentModelBO = agentService.searchAgentModels(agentId);
        try {
            if (agentModelBO.getModelType() == 0) {
                JSONObject params = new JSONObject();
                params.put("modelIds", agentModelBO.getModelIds());
                params.put("userText", userText);
                JSONObject response = aiieService.postResponse(qaaiUrl, "/qaai/predict", params);
                if (response != null && response.containsKey("retData")
                        && response.containsKey("retCode") && response.getIntValue("retCode") == 21201) {
                    return Result.success(20724, "问答问题成功", response.getJSONObject("retData"));
                } else {
                    return Result.fail(40724, "回答问题失败");
                }
            } else if (agentModelBO.getModelType() == 1) {
                JSONObject params = new JSONObject();
                params.put("modelIds", agentModelBO.getModelIds());
                params.put("userText", userText);
                JSONObject response = aiieService.postResponse(kgaiUrl, "/kgai/predict", params);
                if (response != null && response.containsKey("retData")
                        && response.containsKey("retCode") && response.getIntValue("retCode") == 21301) {
                    return Result.success(20724, "问答问题成功", response.getJSONObject("retData"));
                } else {
                    return Result.fail(40724, "回答问题失败");
                }
            } else {
                return Result.fail(40724, "模型不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(500, "服务器出错");
        }
    }

    /**
     *
     * 前端调用该接口来记录对话信息。
     *
     * @param sessionId sessionId
     * @param userId userId
     * @param agentId agentId
     * @param message 对话文本
     * @param sender 标识消息发出者，0：表示用户发出；1：表示agent发出
     * @param ifDeliverd 标识消息发出是否成功
     * @param other 补充字段
     * @return Result
     */
    @ApiOperation(value = "user侧记录历史对话信息接口（user用）", notes = "前端调用该接口来记录对话信息。包括user说的文本和agent返回的文本")
    @PostMapping("/addHistoryDialogue")
    public Result addHistoryDialogue(@RequestParam("sessionId") @ApiParam(value = "sessionId", required = true) String sessionId,
                                     @RequestParam("userId") @ApiParam(value = "userId", required = true) String userId,
                                     @RequestParam("agentId") @ApiParam(value = "agentId", required = true) Integer agentId,
                                     @RequestParam("message") @ApiParam(value = "message", required = true) String message,
                                     @RequestParam("sender") @ApiParam(value = "sender", required = true) Short sender,
                                     @RequestParam("ifDeliverd") @ApiParam(value = "ifDeliverd", defaultValue = "ture") Boolean ifDeliverd,
                                     @RequestParam("other") @ApiParam(value = "other", defaultValue = "") String other ){
        if (!agentService.isAgentExist(agentId)) {
            return Result.fail(40718, String.format("Agent : %d 不存在", agentId));
        }

        HistoryDialogueInfo historyDialogueInfo = historyDialogueService.newHistoryRecode(sessionId, userId, agentId.longValue(), message, sender,ifDeliverd,other);

        try {
            if (historyDialogueInfo == null){
                return Result.fail(40718, "记录历史对话信息失败");
            } else {
                return Result.success(20718, "记录历史对话信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(500, "服务器出错");
        }
    }

    /**
     *
     * 前端根据问题id，获取问题答案返回给用户
     *
     * @param sessionId sessionId
     * @param userId userId
     * @param agentId agentId，用于判断agent是否存在，已经模型类型是否是QA模型
     * @param qapid 问题id
     * @return Result
     */
    @ApiOperation(value = "前端获取QA问题答案接口（user用）",notes = "前端根据问题id，获取问题答案返回给用户")
    @PostMapping("/getQAAnswer")
    public Result getQAAnswer(@RequestParam("sessionId") @ApiParam(value = "sessionId", required = true) String sessionId,
                              @RequestParam("userId") @ApiParam(value = "userId", required = true) String userId,
                              @RequestParam("agentId") @ApiParam(value = "agentId", required = true) Integer agentId,
                              @RequestParam("qapid") @ApiParam(value = "qapid", required = true) String qapid){
        if (!agentService.isAgentExist(agentId)) {
            return Result.fail(40721, String.format("Agent : %d 不存在", agentId));
        }

        AgentModelBO agentModelBO = agentService.searchAgentModels(agentId);
        if (agentModelBO == null){
            return Result.fail(40721, String.format("Agent : %d 信息错误", agentId));
        } else if(agentModelBO.getModelType() != 0){
            return Result.fail(40721, String.format("Agent : %d 模型信息不正确", agentId));
        } else {
            JSONObject params = new JSONObject();
            params.put("qapid", qapid);
            JSONObject response = aiieService.postResponse(qaaiUrl, "/qaai/id2Answer", params);
            if (response != null && response.containsKey("retData")
                    && response.containsKey("retCode") && response.getIntValue("retCode") == 21200) {
                return Result.success(20721, "获取问题答案成功", response.getString("retData"));
            } else {
                return Result.fail(40721, "获取问题答案成功");
            }
        }
    }


    /**
     * 前端调用该接口来记录模型反馈
     * 对于QA模型，反馈记录用户选择想要的正确问题，选择未找到相关问题则记录模型预测结果
     * 对于KG模型，反馈记录用户选择想要的正确答案，选择答案不正确则记录模型原本的答案
     *
     * @param sessionId sessionId
     * @param userId userId
     * @param agentId agentId，用于判断模型类型
     * @param predict 0表示用户没有找到答案，1表示用户找到答案. -1未判断
     * @param userText 用户的问题文本
     * @param answer 用户选择的问题或者答案或者模型原本的预测结果，对于QA模型，用户选择正确的问题；对于KG模型，用户选择正确的答案；对于预测失败的结果返回模型本身预测结果
     * @return Result
     */
    @ApiOperation(value = "user侧模型反馈接口（user用）", notes = "前端调用该接口来记录模型反馈。" +
            "对于QA模型，对于QA模型，反馈记录用户选择想要的正确问题，选择未找到相关问题则记录失败反馈；" +
            "对于KG模型，反馈记录用户选择想要的正确答案，选择答案不正确则记录失败反馈")
    @PostMapping("/feedback")
    public Result getAnswer(@RequestParam("sessionId") @ApiParam(value = "sessionId", required = true) String sessionId,
                            @RequestParam("userId") @ApiParam(value = "userId", required = true) String userId,
                            @RequestParam("agentId") @ApiParam(value = "agentId", required = true) Integer agentId,
                            @RequestParam("predict") @ApiParam(value = "predict", required = true) Integer predict,
                            @RequestParam("userText") @ApiParam(value = "userText", required = true) String userText,
                            @RequestParam("answer") @ApiParam(value = "answer", required = false) String answer
    ) {
        if (!agentService.isAgentExist(agentId)) {
            return Result.fail(41002, String.format("Agent : %d 不存在", agentId));
        }

        AgentModelBO agentModelBO = agentService.searchAgentModels(agentId);

        ModelFeedbackInfo modelFeedbackInfo = modelFeedbackService.newModelFeedback(agentModelBO.getModelType(), userText, answer, predict);

        try {
            if (modelFeedbackInfo == null){
                return Result.fail(41002, "记录模型反馈信息失败");
            } else {
                return Result.success(21002, "记录模型反馈信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(500, "服务器出错");
        }
    }



    /////////////// 模型引擎交互接口 ////////////////////

    /**
     *
     * 获取目前现有的模型，用于agent绑定模型
     *
     * @return Result
     */
    @ApiOperation(value = "获取目前现有的模型（agent用）", notes = "获取目前现有的模型")
    @PostMapping("/getModels")
    public Result getModels() {
        JSONArray jsonArray = new JSONArray();

        try {
            // 获取QA模型
            JSONObject response = aiieService.getResponse(qaaiUrl, "/qaai/getModels");
            if (response != null) {
                System.out.println(response);
                if (response.containsKey("retData")) {
                    JSONArray res = response.getJSONArray("retData");
                    if (res != null) {
                        jsonArray.addAll(res);
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

    /**
     *
     * @param agentId agentid,用于获取模型id和模型类型
     * @param userText 用户预测文本
     * @return Result
     */
    @ApiOperation(value = "获取模型预测结果（agent测试用）", notes = "根据模型类型和ID获取模型预测结果，ID可多个，即返回多个模型综合预测结果")
    @PostMapping("/testPredict")
    public Result testPredict(@RequestParam("agentId") @ApiParam(value = "agentId", required = true) Integer agentId,
                          @RequestParam("userText") @ApiParam(value = "userText", required = true) String userText) {

        if (!agentService.isAgentExist(agentId)) {
            return Result.fail(41101, String.format("Agent : %d 不存在", agentId));
        }

        //敏感词匹配
        if (GlobalCache.sensitiveFilter.filter(userText)) {
            return Result.fail(41101, "回答包含敏感词");
        }

        AgentModelBO agentModelBO = agentService.searchAgentModels(agentId);

        try {
            if (agentModelBO.getModelType() == 0) {
                JSONObject params = new JSONObject();
                params.put("modelIds", agentModelBO.getModelIds());
                params.put("userText", userText);
                JSONObject response = aiieService.postResponse(qaaiUrl, "/qaai/predict", params);
                if (response != null && response.containsKey("retData")
                        && response.containsKey("retCode") && response.getIntValue("retCode") == 21201) {
                    return Result.success(21101, "问答问题成功", response.getJSONObject("retData"));
                } else {
                    return Result.fail(41101, "回答问题失败");
                }
            } else if (agentModelBO.getModelType() == 1) {
                JSONObject params = new JSONObject();
                params.put("modelIds", agentModelBO.getModelIds());
                params.put("userText", userText);
                JSONObject response = aiieService.postResponse(kgaiUrl, "/kgai/predict", params);
                if (response != null && response.containsKey("retData")
                        && response.containsKey("retCode") && response.getIntValue("retCode") == 21301) {
                    return Result.success(21101, "问答问题成功", response.getJSONObject("retData"));
                } else {
                    return Result.fail(41101, "回答问题失败");
                }
            } else {
                return Result.fail(41101, "模型不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(500, "服务器出错");
        }
    }

    ////////////////// 日志记录相关接口 ////////////////////

    /**
     *
     * @param userId 要查询的相关user的userId
     * @param agentId 要查询的相关agent的agentId
     * @param date 要查询的日期，null则查询所有日期
     * @param message 要查询的关键词，用于模糊匹配
     * @return Result
     */
    @ApiOperation(value = "查询历史对话信息")
    @PostMapping("/getHistoryDialogue")
    public Result getHistoryDialogue(@RequestParam("userId") @ApiParam(value = "userId", required = true) String userId,
                                     @RequestParam("agentId") @ApiParam(value = "agentId", required = true) Integer agentId,
                                     @RequestParam("date") @ApiParam(value = "date") @DateTimeFormat Date date,
                                     @RequestParam("message") @ApiParam(value = "message") String message) {
        if (!agentService.isAgentExist(agentId)) {
            return Result.fail(40717, String.format("Agent : %d 不存在", agentId));
        }

        try {
            List<HistoryDialogueInfoBO> searchResult = historyDialogueService.search(agentId.longValue(), userId, date, message);
            if (searchResult != null) {
                return Result.success(20717, "查询历史对话成功", searchResult);
            } else {
                return Result.fail(40717, "查询历史对话失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(500, "服务器出错");
        }
    }


}
