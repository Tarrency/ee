package com.cusc.cuscai.mapper;

import com.cusc.cuscai.entity.bo.AgentMountQABO;
import com.cusc.cuscai.entity.model.AgentMountQA;
import com.cusc.cuscai.entity.model.AgentMountQAExample;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgentMountQAMapper {
    int countByExample(AgentMountQAExample example);

    int deleteByExample(AgentMountQAExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AgentMountQA record);

    int insertSelective(AgentMountQA record);

    List<AgentMountQABO> selectByExample(AgentMountQAExample example);

    AgentMountQABO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AgentMountQA record, @Param("example") AgentMountQAExample example);

    int updateByExample(@Param("record") AgentMountQA record, @Param("example") AgentMountQAExample example);

    int updateByPrimaryKeySelective(AgentMountQA record);

    int updateByPrimaryKey(AgentMountQA record);
}