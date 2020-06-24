package com.cusc.cuscai.entity.model;

import org.noodle.base.BaseModel;

public class AgentMountQA extends BaseModel {
    /** privaryKey **/
    private Integer id;

    /** agent id **/
    private Integer agentId;

    /** qaÖªÊ¶¿â±íid **/
    private Integer qaId;

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

    public Integer getQaId() {
        return qaId;
    }

    public void setQaId(Integer qaId) {
        this.qaId = qaId;
    }
}