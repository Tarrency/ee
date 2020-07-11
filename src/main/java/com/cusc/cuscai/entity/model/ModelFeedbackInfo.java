package com.cusc.cuscai.entity.model;

import java.util.Date;
import org.noodle.base.BaseModel;

public class ModelFeedbackInfo extends BaseModel {
    /**  **/
    private Long feedbackId;

    /** 模型类型[0:QA;1:KG,2:MR] **/
    private Integer modelType;

    /** 消息内容 **/
    private String message;

    /** 模型预测内容 **/
    private String answer;

    /** 模型类型预测是否正确[-1:未记录，0:错误，1:正确] **/
    private Integer predict;

    /** 更新时间 **/
    private Date feedbackUpdateTime;

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Integer getModelType() {
        return modelType;
    }

    public void setModelType(Integer modelType) {
        this.modelType = modelType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getPredict() {
        return predict;
    }

    public void setPredict(Integer predict) {
        this.predict = predict;
    }

    public Date getFeedbackUpdateTime() {
        return feedbackUpdateTime;
    }

    public void setFeedbackUpdateTime(Date feedbackUpdateTime) {
        this.feedbackUpdateTime = feedbackUpdateTime;
    }
}