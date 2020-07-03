package com.cusc.cuscai.service;

import com.cusc.cuscai.dao.QAKnowledgeDao;
import com.cusc.cuscai.entity.bo.KnowledgeInfoBO;
import com.cusc.cuscai.entity.model.KnowledgeBase;
import com.cusc.cuscai.entity.model.KnowledgeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QAknowledgeService {

    @Autowired
    private QAKnowledgeDao qaKnowledgeDao;

    public void newKB(String QAKBName){
        KnowledgeBase newkb = new KnowledgeBase();
        newkb.setKBName(QAKBName);
        qaKnowledgeDao.saveKB(newkb);
    }

    public int insertKnowledge(Integer KBID,List<String> knowledges){
        List<KnowledgeInfo> knowledgeList = new ArrayList<KnowledgeInfo>();
        for(String knowledgeStr:knowledges){
            KnowledgeInfo knowledge = new KnowledgeInfo(KBID,knowledgeStr);
            knowledgeList.add(knowledge);
        }
        return qaKnowledgeDao.insertBatch(knowledgeList);
    }

    public void updateKnowledge(int knowledgeId,String question,String answer,String type){
        KnowledgeInfo knowledgeInfo = new KnowledgeInfo();
        knowledgeInfo.setKnowledgeId(knowledgeId);
        knowledgeInfo.setQuestion(question);
        knowledgeInfo.setAnswer(answer);
        knowledgeInfo.setType(type);
        qaKnowledgeDao.save(knowledgeInfo);
    }

    public void deleteKnowledgeBase(List<Integer> knowledgeBaseIds) {
        qaKnowledgeDao.deleteByKBIds(knowledgeBaseIds);
    }

    public List<KnowledgeInfoBO> getKnowledgeList(String queryKey,String queryName){
        List<KnowledgeInfoBO> resdata = new ArrayList<KnowledgeInfoBO>();
        if (queryKey == null || queryKey.length() ==0)
            resdata = qaKnowledgeDao.findAll(queryName);
        else
            resdata = qaKnowledgeDao.findByKeywordandKBname(queryKey,queryName);
        return resdata;
    }

    public void deleteKnowledge(List<Integer> knowledgeIds) {
        qaKnowledgeDao.deleteByKIIds(knowledgeIds);
    }
}
