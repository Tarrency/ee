package com.cusc.cuscai.entity.model;

import org.noodle.base.BaseModel;

public class KGDBinfo extends BaseModel {
    /**  **/
    private Integer id;

    /**  **/
    private String dbname;

    /**  **/
    private Integer entities;

    /**  **/
    private Integer relationships;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public Integer getEntities() {
        return entities;
    }

    public void setEntities(Integer entities) {
        this.entities = entities;
    }

    public Integer getRelationships() {
        return relationships;
    }

    public void setRelationships(Integer relationships) {
        this.relationships = relationships;
    }
}