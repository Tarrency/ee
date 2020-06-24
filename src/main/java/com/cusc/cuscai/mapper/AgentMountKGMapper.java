package com.cusc.cuscai.mapper;

import com.cusc.cuscai.entity.bo.AgentMountKGBO;
import com.cusc.cuscai.entity.model.AgentMountKG;
import com.cusc.cuscai.entity.model.AgentMountKGExample;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgentMountKGMapper {
    int countByExample(AgentMountKGExample example);

    int deleteByExample(AgentMountKGExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AgentMountKG record);

    int insertSelective(AgentMountKG record);

    List<AgentMountKGBO> selectByExample(AgentMountKGExample example);

    AgentMountKGBO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AgentMountKG record, @Param("example") AgentMountKGExample example);

    int updateByExample(@Param("record") AgentMountKG record, @Param("example") AgentMountKGExample example);

    int updateByPrimaryKeySelective(AgentMountKG record);

    int updateByPrimaryKey(AgentMountKG record);
}