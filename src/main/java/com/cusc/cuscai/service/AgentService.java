package com.cusc.cuscai.service;

import com.cusc.cuscai.dao.AgentInfoDao;
import com.cusc.cuscai.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    @Autowired
    private AgentInfoDao agentInfoDao;

    public boolean isAgentExist(Integer agentId){
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
}
