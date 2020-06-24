package com.cusc.cuscai.dao;

import com.cusc.cuscai.entity.bo.AgentMountKGBO;
import com.cusc.cuscai.entity.model.AgentMountKG;
import com.cusc.cuscai.entity.model.AgentMountKGExample;
import com.cusc.cuscai.mapper.AgentMountKGMapper;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AgentMountKGDao {

	@Autowired
	private AgentMountKGMapper mapper;

   //////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
	public List<AgentMountKGBO> findAll(){
		AgentMountKGExample example=new AgentMountKGExample();
		return mapper.selectByExample(example);
	}
	List<AgentMountKGBO> findAll(Iterable<Integer> ids){
		AgentMountKGExample example=new AgentMountKGExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		AgentMountKGExample example=new AgentMountKGExample();
		return mapper.countByExample(example);
	}

	public List<AgentMountKG> save(Iterable<AgentMountKG> entities){
		List<AgentMountKG> list=new ArrayList<AgentMountKG>();
		for (AgentMountKG AgentMountKG : entities) {
			list.add(save(AgentMountKG));
		}
		return list;
	}
	
	public AgentMountKG save(AgentMountKG record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(AgentMountKG record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public AgentMountKGBO findOne(Integer id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(Integer id){
		if(id == null){
			return false;
		}
		AgentMountKGExample example=new AgentMountKGExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(Integer id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(Integer id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(AgentMountKG entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<AgentMountKG> entities){
		List<Integer> ids=Lists.newArrayList();
		for (AgentMountKG  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<Integer> ids){
		AgentMountKGExample example=new AgentMountKGExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		AgentMountKGExample example=new AgentMountKGExample();
		mapper.deleteByExample(example);
	}
	//////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
}
