package com.cusc.cuscai.mapper;

import com.cusc.cuscai.entity.bo.AgentInfoBO;
import com.cusc.cuscai.entity.model.AgentInfo;
import com.cusc.cuscai.entity.model.AgentInfoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AgentInfoMapper {
    int countByExample(AgentInfoExample example);

    int deleteByExample(AgentInfoExample example);

    int deleteByPrimaryKey(Integer agentId);

    int insert(AgentInfo record);

    int insertSelective(AgentInfo record);

    List<AgentInfoBO> selectByExample(AgentInfoExample example);

    AgentInfoBO selectByPrimaryKey(Integer agentId);

    int updateByExampleSelective(@Param("record") AgentInfo record, @Param("example") AgentInfoExample example);

    int updateByExample(@Param("record") AgentInfo record, @Param("example") AgentInfoExample example);

    int updateByPrimaryKeySelective(AgentInfo record);

    int updateByPrimaryKey(AgentInfo record);
}