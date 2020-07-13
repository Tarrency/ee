package com.cusc.cuscai.mapper;

import com.cusc.cuscai.entity.bo.KGDBinfoBO;
import com.cusc.cuscai.entity.model.KGDBinfo;
import com.cusc.cuscai.entity.model.KGDBinfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KGDBinfoMapper {
    int countByExample(KGDBinfoExample example);

    int deleteByExample(KGDBinfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(KGDBinfo record);

    int insertSelective(KGDBinfo record);

    List<KGDBinfoBO> selectByExample(KGDBinfoExample example);

    KGDBinfoBO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") KGDBinfo record, @Param("example") KGDBinfoExample example);

    int updateByExample(@Param("record") KGDBinfo record, @Param("example") KGDBinfoExample example);

    int updateByPrimaryKeySelective(KGDBinfo record);

    int updateByPrimaryKey(KGDBinfo record);
}