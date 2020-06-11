package com.cusc.cuscai.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class WordInfo {
    /**  **/
    private Integer wordId;

    /**  **/
    @JsonIgnore
    private Integer vocabularyId;

    /**  **/
    private String word;

    /**  **/
    private Date wordUpdateTime;

    public WordInfo(){};

    public WordInfo(int vcbid,String w){
        this.word=w;
        this.vocabularyId=vcbid;
        this.wordUpdateTime=new Date();
    };

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public Integer getVocabularyId() {
        return vocabularyId;
    }

    public void setVocabularyId(Integer vocabularyId) {
        this.vocabularyId = vocabularyId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Date getWordUpdateTime() {
        return wordUpdateTime;
    }

    public void setWordUpdateTime(Date wordUpdateTime) {
        this.wordUpdateTime = wordUpdateTime;
    }
}