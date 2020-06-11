package com.cusc.cuscai.dao;

import java.util.ArrayList;
import java.util.List;

import com.cusc.cuscai.entity.bo.VocabularyInfoBO;
import com.cusc.cuscai.entity.model.VocabularyInfo;
import com.cusc.cuscai.entity.model.VocabularyInfoExample;
import com.cusc.cuscai.mapper.VocabularyInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;



@Repository
public class VocabularyInfoDao {

	@Autowired
	private VocabularyInfoMapper mapper;

   //////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
	public List<VocabularyInfoBO> findAll(){
		VocabularyInfoExample example=new VocabularyInfoExample();
		return mapper.selectByExample(example);
	}
	List<VocabularyInfoBO> findAll(Iterable<java.lang.Integer> ids){
		VocabularyInfoExample example=new VocabularyInfoExample();
		example.createCriteria().andVocabularyIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		VocabularyInfoExample example=new VocabularyInfoExample();
		return mapper.countByExample(example);
	}

	public List<VocabularyInfo> save(Iterable<VocabularyInfo> entities){
		List<VocabularyInfo> list=new ArrayList<VocabularyInfo>();
		for (VocabularyInfo VocabularyInfo : entities) {
			list.add(save(VocabularyInfo));
		}
		return list;
	}
	
	public VocabularyInfo save(VocabularyInfo record){
		if(!exists(record.getVocabularyId())){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(VocabularyInfo record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public VocabularyInfoBO findOne(java.lang.Integer id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Integer id){
		if(id == null){
			return false;
		}
		VocabularyInfoExample example=new VocabularyInfoExample();
		example.createCriteria().andVocabularyIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Integer id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Integer id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(VocabularyInfo entity){
		 mapper.deleteByPrimaryKey(entity.getVocabularyId());
	}

	public void delete(Iterable<VocabularyInfo> entities){
		List<java.lang.Integer> ids=Lists.newArrayList();
		for (VocabularyInfo  entity: entities) {
			ids.add(entity.getVocabularyId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Integer> ids){
		VocabularyInfoExample example=new VocabularyInfoExample();
		example.createCriteria().andVocabularyIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		VocabularyInfoExample example=new VocabularyInfoExample();
		mapper.deleteByExample(example);
	}
	//////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
	public List<VocabularyInfoBO> findByType(int type){
		VocabularyInfoExample example=new VocabularyInfoExample();
		example.createCriteria().andTypeEqualTo(type);
		List<VocabularyInfoBO> res= mapper.selectByExample(example);
		return res;
	}

}
