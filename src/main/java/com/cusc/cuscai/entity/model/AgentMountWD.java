package com.cusc.cuscai.entity.model;

import org.noodle.base.BaseModel;

public class AgentMountWD extends BaseModel {
    /** privaryKey **/
    private Integer id;

    /** agent id **/
    private Integer agentId;

    /** 词表id **/
    private Integer wdId;

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

    public Integer getWdId() {
        return wdId;
    }

    public void setWdId(Integer wdId) {
        this.wdId = wdId;
    }
}