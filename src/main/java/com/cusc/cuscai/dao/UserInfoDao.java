package com.cusc.cuscai.dao;

import java.util.ArrayList;
import java.util.List;

import com.cusc.cuscai.entity.bo.UserInfoBO;
import com.cusc.cuscai.entity.model.UserInfo;
import com.cusc.cuscai.entity.model.UserInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;
import org.springframework.util.DigestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cusc.cuscai.mapper.UserInfoMapper;

@Repository
public class UserInfoDao {

	@Autowired
	private UserInfoMapper mapper;

	/**
	 * 根据用户账户返回用户所有的信息
	 * @param userAccount 用户账户
	 * @return 用户所有的信息
	 */
	public UserInfoBO getUserInfo(String userAccount){
		UserInfoExample example = new UserInfoExample();
		example.createCriteria().andUserAccountEqualTo(userAccount);
		List<UserInfoBO> temp = mapper.selectByExample(example);
		UserInfoBO userinfo = new UserInfoBO();
		userinfo.setUserId(-1);
		if(temp.size() == 0)
			return userinfo;
		userinfo = temp.get(0);
		return userinfo;
	}

	/**
	 * 修改用户信息
	 * @param userAccount 用户账号id
	 * @param userName 用户名
	 * @param userPassword 用户密码
	 * @param userPhone 用户手机
	 * @param userMail 用户邮箱
	 */
	public void modifyUser(String userAccount, String userName,
						   String userPassword, String userPhone,
						   String userMail)
	{
		UserInfoExample example = new UserInfoExample();
		example.createCriteria().andUserAccountEqualTo(userAccount);
		UserInfoBO userinfo = mapper.selectByExample(example).get(0);
		if (userName == ""){

		}else if (userName != null){
			userinfo.setUserName(userName);
		}
		if (userPassword == ""){

		}else if (userPassword != null){
			String md5Password = DigestUtils.md5DigestAsHex(userPassword.getBytes());
			userinfo.setUserPassword(md5Password);
		}

		if (userPhone == ""){

		}else if (userPhone != null){
			userinfo.setUserPhone(userPhone);
		}

		if (userMail == ""){

		}else if (userMail != null){
			userinfo.setUserMail(userMail);
		}
		update(userinfo);
	}

   //////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
	public List<UserInfoBO> findAll(){
		UserInfoExample example=new UserInfoExample();
		return mapper.selectByExample(example);
	}
	List<UserInfoBO> findAll(Iterable<Integer> ids){
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
	
	public UserInfoBO findOne(Integer id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(Integer id){
		if(id == null){
			return false;
		}
		UserInfoExample example=new UserInfoExample();
		example.createCriteria().andUserIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(Integer id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(Integer id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(UserInfo entity){
		 mapper.deleteByPrimaryKey(entity.getUserId());
	}

	public void delete(Iterable<UserInfo> entities){
		List<Integer> ids=Lists.newArrayList();
		for (UserInfo  entity: entities) {
			ids.add(entity.getUserId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<Integer> ids){
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
