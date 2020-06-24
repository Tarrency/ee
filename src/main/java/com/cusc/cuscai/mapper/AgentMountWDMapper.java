package com.cusc.cuscai.mapper;

import com.cusc.cuscai.entity.bo.AgentMountWDBO;
import com.cusc.cuscai.entity.model.AgentMountWD;
import com.cusc.cuscai.entity.model.AgentMountWDExample;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgentMountWDMapper {
    int countByExample(AgentMountWDExample example);

    int deleteByExample(AgentMountWDExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AgentMountWD record);

    int insertSelective(AgentMountWD record);

    List<AgentMountWDBO> selectByExample(AgentMountWDExample example);

    AgentMountWDBO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AgentMountWD record, @Param("example") AgentMountWDExample example);

    int updateByExample(@Param("record") AgentMountWD record, @Param("example") AgentMountWDExample example);

    int updateByPrimaryKeySelective(AgentMountWD record);

    int updateByPrimaryKey(AgentMountWD record);
}