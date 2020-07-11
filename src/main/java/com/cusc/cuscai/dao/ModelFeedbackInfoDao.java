package com.cusc.cuscai.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import com.cusc.cuscai.mapper.ModelFeedbackInfoMapper;
import com.cusc.cuscai.entity.model.ModelFeedbackInfo;
import com.cusc.cuscai.entity.bo.ModelFeedbackInfoBO;
import com.cusc.cuscai.entity.model.ModelFeedbackInfoExample;

@Repository
public class ModelFeedbackInfoDao {

	@Autowired
	private ModelFeedbackInfoMapper mapper;

   //////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
	public List<ModelFeedbackInfoBO> findAll(){
		ModelFeedbackInfoExample example=new ModelFeedbackInfoExample();
		return mapper.selectByExample(example);
	}
	List<ModelFeedbackInfoBO> findAll(Iterable<java.lang.Long> ids){
		ModelFeedbackInfoExample example=new ModelFeedbackInfoExample();
		example.createCriteria().andFeedbackIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		ModelFeedbackInfoExample example=new ModelFeedbackInfoExample();
		return mapper.countByExample(example);
	}

	public List<ModelFeedbackInfo> save(Iterable<ModelFeedbackInfo> entities){
		List<ModelFeedbackInfo> list=new ArrayList<ModelFeedbackInfo>();
		for (ModelFeedbackInfo ModelFeedbackInfo : entities) {
			list.add(save(ModelFeedbackInfo));
		}
		return list;
	}
	
	public ModelFeedbackInfo save(ModelFeedbackInfo record){
		if(!exists(record.getFeedbackId())){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(ModelFeedbackInfo record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public ModelFeedbackInfoBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		ModelFeedbackInfoExample example=new ModelFeedbackInfoExample();
		example.createCriteria().andFeedbackIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(ModelFeedbackInfo entity){
		 mapper.deleteByPrimaryKey(entity.getFeedbackId());
	}

	public void delete(Iterable<ModelFeedbackInfo> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (ModelFeedbackInfo  entity: entities) {
			ids.add(entity.getFeedbackId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		ModelFeedbackInfoExample example=new ModelFeedbackInfoExample();
		example.createCriteria().andFeedbackIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		ModelFeedbackInfoExample example=new ModelFeedbackInfoExample();
		mapper.deleteByExample(example);
	}
	//////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
}
