package com.cusc.cuscai.entity.apibo;

import com.cusc.cuscai.entity.bo.AgentInfoBO;

import java.util.Date;
import java.util.List;

/**
 * @author lxy
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/6/21
 * @discription agentInfo:用于api返回的agent数据，根据接口要求做了封装
 * @usage
 */
public class AgentBO {
    int agentId;
    String agentName;
    Date agentCreateTime;
    Date agentUpdateTime;
    int agentStatus;//开启/关闭
    int modelType;
    List<String> modelIds;

    public AgentBO() {
    }

    public AgentBO(AgentInfoBO agentInfoBO, List<String> modelIds) {
        this.agentId = agentInfoBO.getAgentId();
        this.agentName = agentInfoBO.getAgentName();
        this.agentCreateTime = agentInfoBO.getAgentCreateTime();
        this.agentUpdateTime = agentInfoBO.getAgentUpdateTime();
        this.agentStatus = agentInfoBO.getAgentStatus();
        this.modelType = agentInfoBO.getModelType();
        this.modelIds = modelIds;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
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

    public int getAgentStatus() {
        return agentStatus;
    }

    public void setAgentStatus(int agentStatus) {
        this.agentStatus = agentStatus;
    }

    public int getModelType() {
        return modelType;
    }

    public void setModelType(int modelType) {
        this.modelType = modelType;
    }

    public List<String> getModelIds() {
        return modelIds;
    }

    public void setModelIds(List<String> modelIds) {
        this.modelIds = modelIds;
    }
}
