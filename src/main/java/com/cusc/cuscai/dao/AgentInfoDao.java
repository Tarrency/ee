package com.cusc.cuscai.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import com.cusc.cuscai.mapper.AgentInfoMapper;
import com.cusc.cuscai.entity.model.AgentInfo;
import com.cusc.cuscai.entity.bo.AgentInfoBO;
import com.cusc.cuscai.entity.model.AgentInfoExample;

@Repository
public class AgentInfoDao {

	@Autowired
	private AgentInfoMapper mapper;

   //////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
	public List<AgentInfoBO> findAll(){
		AgentInfoExample example=new AgentInfoExample();
		return mapper.selectByExample(example);
	}
	List<AgentInfoBO> findAll(Iterable<java.lang.Integer> ids){
		AgentInfoExample example=new AgentInfoExample();
		example.createCriteria().andAgentIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		AgentInfoExample example=new AgentInfoExample();
		return mapper.countByExample(example);
	}

	public List<AgentInfo> save(Iterable<AgentInfo> entities){
		List<AgentInfo> list=new ArrayList<AgentInfo>();
		for (AgentInfo AgentInfo : entities) {
			list.add(save(AgentInfo));
		}
		return list;
	}
	
	public AgentInfo save(AgentInfo record){
		if(!exists(record.getAgentId())){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(AgentInfo record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public AgentInfoBO findOne(java.lang.Integer id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Integer id){
		if(id == null){
			return false;
		}
		AgentInfoExample example=new AgentInfoExample();
		example.createCriteria().andAgentIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Integer id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Integer id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(AgentInfo entity){
		 mapper.deleteByPrimaryKey(entity.getAgentId());
	}

	public void delete(Iterable<AgentInfo> entities){
		List<java.lang.Integer> ids=Lists.newArrayList();
		for (AgentInfo  entity: entities) {
			ids.add(entity.getAgentId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Integer> ids){
		AgentInfoExample example=new AgentInfoExample();
		example.createCriteria().andAgentIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		AgentInfoExample example=new AgentInfoExample();
		mapper.deleteByExample(example);
	}
	//////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
}
