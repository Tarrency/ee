package com.cusc.cuscai.entity.apibo;

import com.cusc.cuscai.entity.bo.VocabularyInfoBO;

/**
 *  接口定义字段名称与数据库不一致，再封装一次
 */
public class VocabularyBO {

    private  int id;
    private String name;
    private int type;

    public VocabularyBO(){}
    public VocabularyBO(VocabularyInfoBO vcb){
        this.id = vcb.getVocabularyId();
        this.name= vcb.getName();
        this.type = vcb.getType();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
