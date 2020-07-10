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

    public KnowledgeInfo(Integer KBID, KnowledgeGet knowledge){
        this.baseId = KBID;
        this.question = knowledge.question;
        this.answer = knowledge.answer;
        this.type = knowledge.type;
        this.knowledgeUpdateTime = new Date();
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
    public String getQuestion(){
        return question;
    }
    public void setAnswer(String answer){
        this.answer = answer;
    }
    public String getAnswer(){
        return answer;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return type;
    }

    public Date getKnowledgeUpdateTime() {
        return knowledgeUpdateTime;
    }

    public void setKnowledgeUpdateTime(Date knowledgeUpdateTime) {
        this.knowledgeUpdateTime = knowledgeUpdateTime;
    }

}
