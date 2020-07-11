package com.cusc.cuscai.mapper;

import com.cusc.cuscai.entity.bo.ModelFeedbackInfoBO;
import com.cusc.cuscai.entity.model.ModelFeedbackInfo;
import com.cusc.cuscai.entity.model.ModelFeedbackInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ModelFeedbackInfoMapper {
    int countByExample(ModelFeedbackInfoExample example);

    int deleteByExample(ModelFeedbackInfoExample example);

    int deleteByPrimaryKey(Long feedbackId);

    int insert(ModelFeedbackInfo record);

    int insertSelective(ModelFeedbackInfo record);

    List<ModelFeedbackInfoBO> selectByExample(ModelFeedbackInfoExample example);

    ModelFeedbackInfoBO selectByPrimaryKey(Long feedbackId);

    int updateByExampleSelective(@Param("record") ModelFeedbackInfo record, @Param("example") ModelFeedbackInfoExample example);

    int updateByExample(@Param("record") ModelFeedbackInfo record, @Param("example") ModelFeedbackInfoExample example);

    int updateByPrimaryKeySelective(ModelFeedbackInfo record);

    int updateByPrimaryKey(ModelFeedbackInfo record);
}