package com.cusc.cuscai.entity.model;

import org.noodle.base.BaseModel;

public class AgentMountMR extends BaseModel {
    /** privaryKey **/
    private Integer id;

    /** agent id **/
    private Integer agentId;

    /** 多轮对话模型的id **/
    private String mrId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getMrId() {
        return mrId;
    }

    public void setMrId(String mrId) {
        this.mrId = mrId;
    }
}