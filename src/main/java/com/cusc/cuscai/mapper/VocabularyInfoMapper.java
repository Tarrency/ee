package com.cusc.cuscai.mapper;


import java.util.List;

import com.cusc.cuscai.entity.bo.VocabularyInfoBO;
import com.cusc.cuscai.entity.model.VocabularyInfo;
import com.cusc.cuscai.entity.model.VocabularyInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VocabularyInfoMapper {
    int countByExample(VocabularyInfoExample example);

    int deleteByExample(VocabularyInfoExample example);

    int deleteByPrimaryKey(Integer vocabularyId);

    int insert(VocabularyInfo record);

    int insertSelective(VocabularyInfo record);

    List<VocabularyInfoBO> selectByExample(VocabularyInfoExample example);

    VocabularyInfoBO selectByPrimaryKey(Integer vocabularyId);

    int updateByExampleSelective(@Param("record") VocabularyInfo record, @Param("example") VocabularyInfoExample example);

    int updateByExample(@Param("record") VocabularyInfo record, @Param("example") VocabularyInfoExample example);

    int updateByPrimaryKeySelective(VocabularyInfo record);

    int updateByPrimaryKey(VocabularyInfo record);
}