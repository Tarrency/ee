package com.cusc.cuscai.dao;

import com.cusc.cuscai.entity.bo.AgentMountWDBO;
import com.cusc.cuscai.entity.model.AgentMountWD;
import com.cusc.cuscai.entity.model.AgentMountWDExample;
import com.cusc.cuscai.mapper.AgentMountWDMapper;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AgentMountWDDao {

	@Autowired
	private AgentMountWDMapper mapper;

   //////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
	public List<AgentMountWDBO> findAll(){
		AgentMountWDExample example=new AgentMountWDExample();
		return mapper.selectByExample(example);
	}
	List<AgentMountWDBO> findAll(Iterable<Integer> ids){
		AgentMountWDExample example=new AgentMountWDExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		AgentMountWDExample example=new AgentMountWDExample();
		return mapper.countByExample(example);
	}

	public List<AgentMountWD> save(Iterable<AgentMountWD> entities){
		List<AgentMountWD> list=new ArrayList<AgentMountWD>();
		for (AgentMountWD AgentMountWD : entities) {
			list.add(save(AgentMountWD));
		}
		return list;
	}
	
	public AgentMountWD save(AgentMountWD record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(AgentMountWD record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public AgentMountWDBO findOne(Integer id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(Integer id){
		if(id == null){
			return false;
		}
		AgentMountWDExample example=new AgentMountWDExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(Integer id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(Integer id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(AgentMountWD entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<AgentMountWD> entities){
		List<Integer> ids=Lists.newArrayList();
		for (AgentMountWD  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<Integer> ids){
		AgentMountWDExample example=new AgentMountWDExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		AgentMountWDExample example=new AgentMountWDExample();
		mapper.deleteByExample(example);
	}
	//////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
}
