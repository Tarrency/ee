package com.cusc.cuscai.mapper;

import com.cusc.cuscai.entity.bo.AgentMountMRBO;
import com.cusc.cuscai.entity.model.AgentMountMR;
import com.cusc.cuscai.entity.model.AgentMountMRExample;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgentMountMRMapper {
    int countByExample(AgentMountMRExample example);

    int deleteByExample(AgentMountMRExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AgentMountMR record);

    int insertSelective(AgentMountMR record);

    List<AgentMountMRBO> selectByExample(AgentMountMRExample example);

    AgentMountMRBO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AgentMountMR record, @Param("example") AgentMountMRExample example);

    int updateByExample(@Param("record") AgentMountMR record, @Param("example") AgentMountMRExample example);

    int updateByPrimaryKeySelective(AgentMountMR record);

    int updateByPrimaryKey(AgentMountMR record);
}