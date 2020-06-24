package com.cusc.cuscai.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cusc.cuscai.dao.AgentInfoDao;
import com.cusc.cuscai.entity.apibo.AgentBO;
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
            System.out.println(e);
            throw new GlobalException(500, "数据库异常！");
        } catch (Exception e) {
            System.out.println(e);
            throw new GlobalException(500, "未知异常！");
        }
    }

    @NonNull
    public List<AgentBO> getList(Integer adminID, String agentName) {
        List<AgentInfoBO> resdata = new ArrayList<>();
        try {
            resdata = agentInfoDao.findByAdminAndName(adminID, agentName);
        } catch (Exception e) {
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
            throw e;
        }
        if (resdata == null || resdata.isEmpty()) {
            return null;
        } else {
            return new AgentBO(resdata.get(0));
        }
    }

    public void deleteAgent(Integer agentID) {
        agentInfoDao.delete(agentID);
    }

    /**
     * agentDatabase = {"agentName":电信，"QA":电信史,"scene":问答,"词表":禁用敏感词库、电信词库}
     */
    public Integer newAgent(Integer adminID, String agentDatabase) {
        JSONObject obj = JSON.parseObject(agentDatabase);
        String agentName = obj.getString("agentName");
        String QA = obj.getString("QA");
        List<Integer> QA_ids = new ArrayList<>();
        for (String s : QA.split(",")) {
            QA_ids.add(Integer.valueOf(s));
        }
        String scene = obj.getString("scene");
        List<Integer> scene_ids = new ArrayList<>();
        for (String s : scene.split(",")) {
            scene_ids.add(Integer.valueOf(s));
        }
        String voc = obj.getString("voc");
        List<Integer> voc_ids = new ArrayList<>();
        for (String s : voc.split(",")) {
            voc_ids.add(Integer.valueOf(s));
        }
        try {
            return agentInfoDao.newAgent(adminID, agentName, QA_ids, scene_ids, voc_ids);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * agentDatabase = {"agentName":电信，"QA":电信史,"scene":问答,"词表":禁用敏感词库、电信词库}
     */
    public void changeAgent(Integer adminID, Integer agentID, String agentDatabase) {
        JSONObject obj = JSON.parseObject(agentDatabase);
        String agentName = obj.getString("agentName");
        String QA = obj.getString("QA");
        List<Integer> QA_ids = new ArrayList<>();
        for (String s : QA.split(",")) {
            QA_ids.add(Integer.valueOf(s));
        }
        String scene = obj.getString("scene");
        List<Integer> scene_ids = new ArrayList<>();
        for (String s : scene.split(",")) {
            scene_ids.add(Integer.valueOf(s));
        }
        String voc = obj.getString("voc");
        List<Integer> voc_ids = new ArrayList<>();
        for (String s : voc.split(",")) {
            voc_ids.add(Integer.valueOf(s));
        }
        try {
            agentInfoDao.changeAgent(adminID, agentID, agentName, QA_ids, scene_ids, voc_ids);
        } catch (Exception e) {
            throw e;
        }
    }
}
