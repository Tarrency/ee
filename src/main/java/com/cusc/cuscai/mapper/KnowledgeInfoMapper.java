package com.cusc.cuscai.mapper;


import com.cusc.cuscai.entity.bo.KnowledgeInfoBO;
import com.cusc.cuscai.entity.model.KnowledgeBaseExample;
import com.cusc.cuscai.entity.model.KnowledgeBase;
import com.cusc.cuscai.entity.model.KnowledgeInfoExample;
import com.cusc.cuscai.entity.model.KnowledgeInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KnowledgeInfoMapper {
    int countByExample(KnowledgeInfoExample example);

    int insertSelectiveKB(KnowledgeBase record);

    int updateByPrimaryKeySelectiveKB(KnowledgeBase record);

    int insertSelectiveKI(KnowledgeInfo record);

    int updateByPrimaryKeySelectiveKI(KnowledgeInfo record);

    int insertBatch(List<KnowledgeInfo> list);

    List<KnowledgeInfoBO> selectByExample(KnowledgeInfoExample example);

    int deleteByKIExample(KnowledgeInfoExample example);

    int deleteByKBExample(KnowledgeBaseExample example);
}
