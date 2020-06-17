package com.cusc.cuscai.entity.apibo;

import com.cusc.cuscai.entity.bo.WordInfoBO;

import java.util.Date;

/**
 *  接口定义字段名称与数据库不一致，再封装一次
 */
public class WordBO {
    private int id;
    private String word;
    private Date update_time;

    public WordBO(){};
    public WordBO(WordInfoBO info){
        this.id=info.getWordId();
        this.word=info.getWord();
        this.update_time=info.getWordUpdateTime();
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
