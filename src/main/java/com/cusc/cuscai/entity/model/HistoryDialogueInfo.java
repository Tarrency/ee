package com.cusc.cuscai.entity.model;

import java.util.Date;
import org.noodle.base.BaseModel;

public class HistoryDialogueInfo extends BaseModel {
    /**  **/
    private Long id;

    /**  **/
    private String sessionId;

    /**  **/
    private String userId;

    /**  **/
    private Long agentId;

    /** 消息内容 **/
    private String message;

    /** 消息发送方[0:user;1:agent] **/
    private Short sender;

    /** 更新时间 **/
    private Date date;

    /** 是否发送成功 **/
    private Boolean ifDeliverd;

    /** 补充字段 **/
    private String other;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Short getSender() {
        return sender;
    }

    public void setSender(Short sender) {
        this.sender = sender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getIfDeliverd() {
        return ifDeliverd;
    }

    public void setIfDeliverd(Boolean ifDeliverd) {
        this.ifDeliverd = ifDeliverd;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}