package com.cusc.cuscai.mapper;

import com.cusc.cuscai.entity.bo.KnowledgeBaseBO;
import com.cusc.cuscai.entity.model.KnowledgeBase;
import com.cusc.cuscai.entity.model.KnowledgeBaseExample;

import java.util.List;

public interface KnowledgeBaseMapper {
    int countByExample(KnowledgeBaseExample example);

    int insertSelective(KnowledgeBase record);

    int updateByPrimaryKeySelective(KnowledgeBase record);

    int deleteByPrimaryKey(Integer KnowledgeBaseId);

    List<KnowledgeBaseBO> selectByExample(KnowledgeBaseExample example);
}
