package com.cusc.cuscai.entity.apibo;

import com.cusc.cuscai.entity.bo.AgentInfoBO;

import java.util.Date;

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

    public AgentBO() {
    }

    public AgentBO(AgentInfoBO agentInfoBO) {
        this.agentId = agentInfoBO.getAgentId();
        this.agentName = agentInfoBO.getAgentName();
        this.agentCreateTime = agentInfoBO.getAgentCreateTime();
        this.agentUpdateTime = agentInfoBO.getAgentUpdateTime();
        this.agentStatus = agentInfoBO.getAgentStatus();
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

}
