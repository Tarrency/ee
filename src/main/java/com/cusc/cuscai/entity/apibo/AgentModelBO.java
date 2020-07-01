package com.cusc.cuscai.entity.apibo;

import java.util.List;

/**
 * @author lxy
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/7/1
 * @discription null
 * @usage null
 */
public class AgentModelBO {
    int modelType;
    List<String> modelIds;

    public AgentModelBO() {
    }

    public AgentModelBO(int modelType, List<String> modelIds) {
        this.modelType = modelType;
        this.modelIds = modelIds;
    }

    public int getModelType() {
        return modelType;
    }

    public void setModelType(int modelType) {
        this.modelType = modelType;
    }

    public List<String> getModelIds() {
        return modelIds;
    }

    public void setModelIds(List<String> modelIds) {
        this.modelIds = modelIds;
    }
}
