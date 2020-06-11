package com.cusc.cuscai.entity.model;

import java.util.Date;

public class VocabularyInfo {
    /**  **/
    private Integer vocabularyId;

    /**  词汇表名 **/
    private String name;

    /** 0是专用词表，1是敏感词表 **/
    private Integer type;

    /**  **/
    private Date vocabularyUpdateTime;

    public Integer getVocabularyId() {
        return vocabularyId;
    }

    public void setVocabularyId(Integer vocabularyId) {
        this.vocabularyId = vocabularyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getVocabularyUpdateTime() {
        return vocabularyUpdateTime;
    }

    public void setVocabularyUpdateTime(Date vocabularyUpdateTime) {
        this.vocabularyUpdateTime = vocabularyUpdateTime;
    }
}