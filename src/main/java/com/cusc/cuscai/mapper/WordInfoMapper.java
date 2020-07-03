package com.cusc.cuscai.mapper;


import java.util.List;

import com.cusc.cuscai.entity.bo.WordInfoBO;
import com.cusc.cuscai.entity.model.WordInfo;
import com.cusc.cuscai.entity.model.WordInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface WordInfoMapper {
    int countByExample(WordInfoExample example);

    int deleteByExample(WordInfoExample example);

    int deleteByPrimaryKey(Integer wordId);

    int insert(WordInfo record);

    int insertSelective(WordInfo record);

    List<WordInfoBO> selectByExample(WordInfoExample example);

    WordInfoBO selectByPrimaryKey(Integer wordId);

    int updateByExampleSelective(@Param("record") WordInfo record, @Param("example") WordInfoExample example);

    int updateByExample(@Param("record") WordInfo record, @Param("example") WordInfoExample example);

    int updateByPrimaryKeySelective(WordInfo record);

    int updateByPrimaryKey(WordInfo record);

    int insertBatch(List<WordInfo> list);
}