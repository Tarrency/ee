package com.cusc.cuscai.entity.model;

import java.util.Date;
import java.util.List;

public class KnowledgeInfo {

    private Integer Id;

    private Integer baseId;

    private String question;

    private String answer;

    private String type;

    private Date knowledgeUpdateTime;

    public KnowledgeInfo(){};

    public KnowledgeInfo(Integer KBID, String knowledges){
        this.baseId = KBID;
        this.question = knowledges;
    };

    public Integer getKnowledgeId() {
        return Id;
    }

    public void setKnowledgeId(Integer knowledgeId){
        this.Id = knowledgeId;
    }
    public void setQuestion(String question){
        this.question = question;
    }
    public void setAnswer(String answer){
        this.answer = answer;
    }
    public void setType(String type){
        this.type = type;
    }

    public Date getWordUpdateTime() {
        return knowledgeUpdateTime;
    }

    public void setWordUpdateTime(Date knowledgeUpdateTime) {
        this.knowledgeUpdateTime = knowledgeUpdateTime;
    }

}
