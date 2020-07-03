package com.cusc.cuscai.service;

import com.cusc.cuscai.dao.AgentInfoDao;
import com.cusc.cuscai.entity.apibo.AgentBO;
import com.cusc.cuscai.entity.apibo.AgentModelBO;
import com.cusc.cuscai.entity.bo.AgentInfoBO;
import com.cusc.cuscai.exception.GlobalException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgentService {

    @Autowired
    private AgentInfoDao agentInfoDao;

    public boolean isAgentExist(Integer agentId) {
        try {
            return agentInfoDao.exists(agentId);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new GlobalException(500, "数据库异常！");
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(500, "未知异常！");
        }
    }

    @NonNull
    public List<AgentBO> getList(Integer adminID, String agentName) {
        List<AgentInfoBO> resdata = new ArrayList<>();
        try {
            resdata = agentInfoDao.findByAdminAndName(adminID, agentName);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        List<AgentBO> resbo = new ArrayList<>();
        for (AgentInfoBO bo : resdata) {
            AgentModelBO agentModelBO = agentInfoDao.searchAgentModels(bo.getAgentId());
            resbo.add(new AgentBO(bo, agentModelBO.getModelIds()));
        }
        return resbo;
    }

    @Nullable
    public AgentBO search(Integer adminID, Integer agentID) {
        List<AgentInfoBO> resdata = new ArrayList<>();
        try {
            resdata = agentInfoDao.findByAdminAndId(adminID, agentID);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        if (resdata == null || resdata.isEmpty()) {
            return null;
        } else {
            AgentInfoBO bo = resdata.get(0);
            AgentModelBO agentModelBO = agentInfoDao.searchAgentModels(bo.getAgentId());
            return new AgentBO(bo, agentModelBO.getModelIds());
        }
    }

    public void deleteAgent(Integer adminID, Integer agentID) {
        agentInfoDao.delete(adminID, agentID);
    }

    public Integer newAgent(Integer adminID, String agentName, int modelType, List<String> modelIds) {
        try {
            return agentInfoDao.newAgent(adminID, agentName, modelType, modelIds);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void changeAgent(int adminID, int agentID, String agentName, int modelType, List<String> modelIds) {
        try {
            agentInfoDao.changeAgent(adminID, agentID, agentName, modelType, modelIds);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 查找所有model，并返回modelType
     * @param agentID 必选， agent 的 id
     * @return AgentModelBO
     */
    public AgentModelBO searchAgentModels(int agentID) {
        AgentModelBO resdata;
        try {
            resdata = agentInfoDao.searchAgentModels(agentID);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return resdata;
    }
}
