package com.cusc.cuscai.entity.model;

import org.noodle.base.BaseModel;

public class AgentMountKG extends BaseModel {
    /** primaryKey **/
    private Integer id;

    /** agent id **/
    private Integer agentId;

    /** 知识图谱数据表id **/
    private Integer kgId;

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

    public Integer getKgId() {
        return kgId;
    }

    public void setKgId(Integer kgId) {
        this.kgId = kgId;
    }
}