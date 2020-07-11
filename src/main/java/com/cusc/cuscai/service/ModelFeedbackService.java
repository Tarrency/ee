package com.cusc.cuscai.service;

import com.cusc.cuscai.dao.ModelFeedbackInfoDao;
import com.cusc.cuscai.entity.model.ModelFeedbackInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ModelFeedbackService {

    @Autowired
    private ModelFeedbackInfoDao modelFeedbackInfoDao;

    public ModelFeedbackInfo newModelFeedback(Integer modelType, String message, String answer, Integer predict){
        ModelFeedbackInfo modelFeedbackInfo = new ModelFeedbackInfo();

        modelFeedbackInfo.setModelType(modelType);
        modelFeedbackInfo.setMessage(message);
        modelFeedbackInfo.setAnswer(answer);
        modelFeedbackInfo.setPredict(predict);
        modelFeedbackInfo.setFeedbackUpdateTime(new Date());

        try {
            return modelFeedbackInfoDao.save(modelFeedbackInfo);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ModelFeedbackInfo updateModelFeedback(Long feedbackId, String message, String answer, Integer predict){
        if (feedbackId == null){
            return null;
        }

        ModelFeedbackInfo modelFeedbackInfo = new ModelFeedbackInfo();

        modelFeedbackInfo.setFeedbackId(feedbackId);
        modelFeedbackInfo.setMessage(message);
        modelFeedbackInfo.setAnswer(answer);
        modelFeedbackInfo.setPredict(predict);
        modelFeedbackInfo.setFeedbackUpdateTime(new Date());

        try {
            return modelFeedbackInfoDao.save(modelFeedbackInfo);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
