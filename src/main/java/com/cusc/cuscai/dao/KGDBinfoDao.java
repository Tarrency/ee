package com.cusc.cuscai.dao;

import com.cusc.cuscai.entity.bo.KGDBinfoBO;
import com.cusc.cuscai.entity.model.KGDBinfo;
import com.cusc.cuscai.entity.model.KGDBinfoExample;
import com.cusc.cuscai.mapper.KGDBinfoMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class KGDBinfoDao {

	@Autowired
	private KGDBinfoMapper mapper;

   //////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
	public List<KGDBinfoBO> findAll(){
		KGDBinfoExample example=new KGDBinfoExample();
		return mapper.selectByExample(example);
	}
	List<KGDBinfoBO> findAll(Iterable<Integer> ids){
		KGDBinfoExample example=new KGDBinfoExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		KGDBinfoExample example=new KGDBinfoExample();
		return mapper.countByExample(example);
	}

	public List<KGDBinfo> save(Iterable<KGDBinfo> entities){
		List<KGDBinfo> list=new ArrayList<KGDBinfo>();
		for (KGDBinfo KGDBinfo : entities) {
			list.add(save(KGDBinfo));
		}
		return list;
	}
	
	public KGDBinfo save(KGDBinfo record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(KGDBinfo record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public KGDBinfoBO findOne(Integer id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(Integer id){
		if(id == null){
			return false;
		}
		KGDBinfoExample example=new KGDBinfoExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(Integer id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(Integer id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(KGDBinfo entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<KGDBinfo> entities){
		List<Integer> ids=Lists.newArrayList();
		for (KGDBinfo  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<Integer> ids){
		KGDBinfoExample example=new KGDBinfoExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		KGDBinfoExample example=new KGDBinfoExample();
		mapper.deleteByExample(example);
	}
	//////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
}
