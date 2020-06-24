package com.cusc.cuscai.entity.apibo;

/**
 * @author lxy
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/6/23
 * @discription 挂载到agent的模型
 * @usage 参考 AgentBO 的约定
 */
public class ModelBO {
    //模型id
    int modelId;
    //模型类型
    int modelType;
    //模型类型
    String modelName;

    public ModelBO() {
    }

    public ModelBO(int modelId, int modelType, String modelName) {
        this.modelId = modelId;
        this.modelType = modelType;
        this.modelName = modelName;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getModelType() {
        return modelType;
    }

    public void setModelType(int modelType) {
        this.modelType = modelType;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
