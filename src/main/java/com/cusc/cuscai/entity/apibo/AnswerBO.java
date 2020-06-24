package com.cusc.cuscai.entity.apibo;

/**
 * @author lxy
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/6/23
 * @discription null
 * @usage null
 */
public class AnswerBO {
    //模型id
    int modelId;
    //模型回答
    String answer;

    public AnswerBO() {
    }

    public AnswerBO(int modelId, String answer) {
        this.modelId = modelId;
        this.answer = answer;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
