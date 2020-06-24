package com.cusc.cuscai.dao;

import com.cusc.cuscai.entity.bo.AgentMountQABO;
import com.cusc.cuscai.entity.model.AgentMountQA;
import com.cusc.cuscai.entity.model.AgentMountQAExample;
import com.cusc.cuscai.mapper.AgentMountQAMapper;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AgentMountQADao {

	@Autowired
	private AgentMountQAMapper mapper;

   //////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
	public List<AgentMountQABO> findAll(){
		AgentMountQAExample example=new AgentMountQAExample();
		return mapper.selectByExample(example);
	}
	List<AgentMountQABO> findAll(Iterable<Integer> ids){
		AgentMountQAExample example=new AgentMountQAExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		AgentMountQAExample example=new AgentMountQAExample();
		return mapper.countByExample(example);
	}

	public List<AgentMountQA> save(Iterable<AgentMountQA> entities){
		List<AgentMountQA> list=new ArrayList<AgentMountQA>();
		for (AgentMountQA AgentMountQA : entities) {
			list.add(save(AgentMountQA));
		}
		return list;
	}
	
	public AgentMountQA save(AgentMountQA record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(AgentMountQA record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public AgentMountQABO findOne(Integer id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(Integer id){
		if(id == null){
			return false;
		}
		AgentMountQAExample example=new AgentMountQAExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(Integer id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(Integer id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(AgentMountQA entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<AgentMountQA> entities){
		List<Integer> ids=Lists.newArrayList();
		for (AgentMountQA  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<Integer> ids){
		AgentMountQAExample example=new AgentMountQAExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		AgentMountQAExample example=new AgentMountQAExample();
		mapper.deleteByExample(example);
	}
	//////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
}
