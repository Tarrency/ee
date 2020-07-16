package com.cusc.cuscai.service;

import com.cusc.cuscai.dao.QAKnowledgeDao;
import com.cusc.cuscai.dao.QAKnowledgeBaseDao;
import com.cusc.cuscai.entity.bo.KnowledgeBaseBO;
import com.cusc.cuscai.entity.bo.KnowledgeInfoBO;
import com.cusc.cuscai.entity.model.KnowledgeBase;
import com.cusc.cuscai.entity.model.KnowledgeGet;
import com.cusc.cuscai.entity.model.KnowledgeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QAknowledgeService {

    @Autowired
    private QAKnowledgeDao qaKnowledgeDao;
    @Autowired
    private QAKnowledgeBaseDao qaKnowledgeBaseDao;

    public void newKB(String QAKBName){
        KnowledgeBase newkb = new KnowledgeBase();
        newkb.setKBName(QAKBName);
        qaKnowledgeBaseDao.saveKB(newkb);
    }

    public int insertKnowledge(Integer KBID,List<KnowledgeGet> knowledges){
        List<KnowledgeInfo> knowledgeList = new ArrayList<KnowledgeInfo>();
        for(KnowledgeGet knowledgeStr:knowledges){
            KnowledgeInfo knowledge = new KnowledgeInfo(KBID,knowledgeStr);
            knowledgeList.add(knowledge);
        }
        return qaKnowledgeDao.insertBatch(knowledgeList);
    }
//修改知识
    public void updateKnowledge(int knowledgeId,String question,String answer,String type){
        KnowledgeInfo knowledgeInfo = new KnowledgeInfo();
        knowledgeInfo.setKnowledgeId(knowledgeId);
        knowledgeInfo.setQuestion(question);
        knowledgeInfo.setAnswer(answer);
        knowledgeInfo.setType(type);
        qaKnowledgeDao.save(knowledgeInfo);
    }

    public void deleteKnowledgeBase(int knowledgeBaseIds) {
        qaKnowledgeBaseDao.delete(knowledgeBaseIds);
    }

    public List<KnowledgeBaseBO> getKnowledgeList(){
        List<KnowledgeBaseBO> resdata = new ArrayList<KnowledgeBaseBO>();
        try {
            resdata = qaKnowledgeBaseDao.findAll();
        }catch (Exception e){
            throw e;
        }
        return resdata;
    }
//获取知识信息
    public List<KnowledgeInfoBO> getKnowledgeInfoList(String queryKey,Integer queryId){
        List<KnowledgeInfoBO> resdata = new ArrayList<KnowledgeInfoBO>();
        if(queryId == null){
            resdata = qaKnowledgeDao.findByKeyword(queryKey);
        }else {
            resdata = qaKnowledgeDao.findByKeywordAndKB(queryKey,queryId);
        }
        return resdata;
    }

    public List<KnowledgeInfoBO> getKnowledgeListByKB(int KbId){
        List<KnowledgeInfoBO> resdata = new ArrayList<KnowledgeInfoBO>();
        resdata = qaKnowledgeDao.findByKBID(KbId);
        return resdata;
    }

    public List<KnowledgeInfoBO> getKnowledgeById(int knowledgeId){
        List<KnowledgeInfoBO> resdata = new ArrayList<KnowledgeInfoBO>();
        resdata = qaKnowledgeDao.findByKnowledgeId(knowledgeId);
        return resdata;
    }

    public void deleteKnowledge(List<Integer> knowledgeIds) {
        qaKnowledgeDao.deleteByKIIds(knowledgeIds);
    }

}
