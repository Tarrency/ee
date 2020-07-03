package com.cusc.cuscai.service;

import com.cusc.cuscai.dao.HistoryDialogueInfoDao;
import com.cusc.cuscai.entity.bo.HistoryDialogueInfoBO;
import com.cusc.cuscai.entity.model.HistoryDialogueInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HistoryDialogueService {

    @Autowired
    private HistoryDialogueInfoDao historyDialogueInfoDao;

    /**
     *
     * @param agentId
     * @param userId
     * @param date
     * @param message
     * @return
     */
    public List<HistoryDialogueInfoBO> search(Long agentId,
                                              String userId,
                                              Date date,
                                              String message){
        try {
            return historyDialogueInfoDao.findByAgentIdAndUserIdAndDateAndUserText(
                    agentId,
                    userId,
                    date,
                    message);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param sessionId
     * @param userId
     * @param agentId
     * @param message
     * @param sender {0：表示用户发送；1：表示agent发送}
     * @return
     */
    public HistoryDialogueInfo newHistoryRecode(String sessionId,
                                                String userId,
                                                Long agentId,
                                                String message,
                                                Short sender){
        HistoryDialogueInfo historyDialogueInfo = new HistoryDialogueInfo();

        historyDialogueInfo.setSessionId(sessionId);
        historyDialogueInfo.setUserId(userId);
        historyDialogueInfo.setAgentId(agentId);
        historyDialogueInfo.setSender(sender);
        historyDialogueInfo.setMessage(message);
        historyDialogueInfo.setDate(new Date());

        try {
            return historyDialogueInfoDao.save(historyDialogueInfo);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
