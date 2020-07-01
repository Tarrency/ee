package com.cusc.cuscai.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
            resbo.add(new AgentBO(bo));
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
            return new AgentBO(resdata.get(0));
        }
    }

    public void deleteAgent(Integer adminID, Integer agentID) {
        agentInfoDao.delete(adminID, agentID);
    }

    /**
     * agentDatabase = {"agentName":电信，"QA":电信史,"scene":问答,"词表":禁用敏感词库、电信词库}
     */
    public Integer newAgent(Integer adminID, String agentDatabase) {
        try {
            JSONObject obj = JSON.parseObject(agentDatabase);
            String agentName = obj.getString("agentName");
            List<Integer> QA_ids = splitAsInteger(obj.getString("QA"));
            List<Integer> scene_ids = splitAsInteger(obj.getString("scene"));
            List<Integer> kg_ids = splitAsInteger(obj.getString("kg"));
            List<Integer> voc_ids = splitAsInteger(obj.getString("voc"));
            return agentInfoDao.newAgent(adminID, agentName, QA_ids, scene_ids, kg_ids, voc_ids);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    List<Integer> splitAsInteger(String text) {
        List<Integer> ids = new ArrayList<>();
        for (String s : text.split(",")) {
            if (!s.isEmpty()) {
                ids.add(Integer.valueOf(s));
            }
        }
        return ids;
    }

    /**
     * agentDatabase = {"agentName":电信，"QA":电信史,"scene":问答,"词表":禁用敏感词库、电信词库}
     */
    public void changeAgent(Integer adminID, Integer agentID, String agentDatabase) {
        try {
            JSONObject obj = JSON.parseObject(agentDatabase);
            String agentName = obj.getString("agentName");
            List<Integer> QA_ids = splitAsInteger(obj.getString("QA"));
            List<Integer> scene_ids = splitAsInteger(obj.getString("scene"));
            List<Integer> kg_ids = splitAsInteger(obj.getString("kg"));
            List<Integer> voc_ids = splitAsInteger(obj.getString("voc"));
            agentInfoDao.changeAgent(adminID, agentID, agentName, QA_ids, scene_ids, kg_ids, voc_ids);
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
        int modelType = 0;//QA
        List<Integer> modelIds = new ArrayList<>();
        AgentModelBO resdata = new AgentModelBO(modelType, modelIds);
        return resdata;
    }
}
