package com.cusc.cuscai.dao;

import java.util.ArrayList;
import java.util.List;

import com.cusc.cuscai.entity.bo.UserInfoBO;
import com.cusc.cuscai.entity.model.UserInfo;
import com.cusc.cuscai.entity.model.UserInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import com.cusc.cuscai.mapper.UserInfoMapper;

@Repository
public class UserInfoDao {

	@Autowired
	private UserInfoMapper mapper;

   //////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
	public List<UserInfoBO> findAll(){
		UserInfoExample example=new UserInfoExample();
		return mapper.selectByExample(example);
	}
	List<UserInfoBO> findAll(Iterable<Long> ids){
		UserInfoExample example=new UserInfoExample();
		example.createCriteria().andUserIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		UserInfoExample example=new UserInfoExample();
		return mapper.countByExample(example);
	}

	public List<UserInfo> save(Iterable<UserInfo> entities){
		List<UserInfo> list=new ArrayList<UserInfo>();
		for (UserInfo UserInfo : entities) {
			list.add(save(UserInfo));
		}
		return list;
	}
	
	public UserInfo save(UserInfo record){
		if(!exists(record.getUserId())){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(UserInfo record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public UserInfoBO findOne(Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(Long id){
		if(id == null){
			return false;
		}
		UserInfoExample example=new UserInfoExample();
		example.createCriteria().andUserIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(UserInfo entity){
		 mapper.deleteByPrimaryKey(entity.getUserId());
	}

	public void delete(Iterable<UserInfo> entities){
		List<Long> ids=Lists.newArrayList();
		for (UserInfo  entity: entities) {
			ids.add(entity.getUserId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<Long> ids){
		UserInfoExample example=new UserInfoExample();
		example.createCriteria().andUserIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		UserInfoExample example=new UserInfoExample();
		mapper.deleteByExample(example);
	}
	//////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
}
