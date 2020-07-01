package com.cusc.cuscai.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import com.cusc.cuscai.mapper.AgentMountMRMapper;
import com.cusc.cuscai.entity.model.AgentMountMR;
import com.cusc.cuscai.entity.bo.AgentMountMRBO;
import com.cusc.cuscai.entity.model.AgentMountMRExample;

@Repository
public class AgentMountMRDao {

	@Autowired
	private AgentMountMRMapper mapper;

   //////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
	public List<AgentMountMRBO> findAll(){
		AgentMountMRExample example=new AgentMountMRExample();
		return mapper.selectByExample(example);
	}
	List<AgentMountMRBO> findAll(Iterable<Integer> ids){
		AgentMountMRExample example=new AgentMountMRExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		AgentMountMRExample example=new AgentMountMRExample();
		return mapper.countByExample(example);
	}

	public List<AgentMountMR> save(Iterable<AgentMountMR> entities){
		List<AgentMountMR> list=new ArrayList<AgentMountMR>();
		for (AgentMountMR AgentMountMR : entities) {
			list.add(save(AgentMountMR));
		}
		return list;
	}
	
	public AgentMountMR save(AgentMountMR record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(AgentMountMR record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public AgentMountMRBO findOne(Integer id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(Integer id){
		if(id == null){
			return false;
		}
		AgentMountMRExample example=new AgentMountMRExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(Integer id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(Integer id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(AgentMountMR entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<AgentMountMR> entities){
		List<Integer> ids=Lists.newArrayList();
		for (AgentMountMR  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<Integer> ids){
		AgentMountMRExample example=new AgentMountMRExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		AgentMountMRExample example=new AgentMountMRExample();
		mapper.deleteByExample(example);
	}
	//////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
}
