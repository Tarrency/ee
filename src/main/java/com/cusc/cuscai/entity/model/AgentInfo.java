package com.cusc.cuscai.entity.model;

import org.noodle.base.BaseModel;

import java.util.Date;

public class AgentInfo extends BaseModel {
    /**  **/
    private Integer agentId;

    /**  **/
    private Integer adminId;

    /** agent名称 **/
    private String agentName;

    /** agent状态 **/
    private Integer agentStatus;

    /** 创建时间 **/
    private Date agentCreateTime;

    /** 更新时间 **/
    private Date agentUpdateTime;

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Integer getAgentStatus() {
        return agentStatus;
    }

    public void setAgentStatus(Integer agentStatus) {
        this.agentStatus = agentStatus;
    }

    public Date getAgentCreateTime() {
        return agentCreateTime;
    }

    public void setAgentCreateTime(Date agentCreateTime) {
        this.agentCreateTime = agentCreateTime;
    }

    public Date getAgentUpdateTime() {
        return agentUpdateTime;
    }

    public void setAgentUpdateTime(Date agentUpdateTime) {
        this.agentUpdateTime = agentUpdateTime;
    }
}