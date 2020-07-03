package com.cusc.cuscai.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import com.cusc.cuscai.mapper.HistoryDialogueInfoMapper;
import com.cusc.cuscai.entity.model.HistoryDialogueInfo;
import com.cusc.cuscai.entity.bo.HistoryDialogueInfoBO;
import com.cusc.cuscai.entity.model.HistoryDialogueInfoExample;

@Repository
public class HistoryDialogueInfoDao {

	@Autowired
	private HistoryDialogueInfoMapper mapper;

	public List<HistoryDialogueInfoBO> findByAgentIdAndUserIdAndDateAndUserText(
			Long agentId,
			String userId,
			Date date,
			String message
	){
		HistoryDialogueInfoExample example=new HistoryDialogueInfoExample();
		HistoryDialogueInfoExample.Criteria criteria = example.createCriteria();

		if (agentId != null){
			criteria = criteria.andAgentIdEqualTo(agentId);
		}
		if (userId != null && !userId.isEmpty()){
			criteria = criteria.andUserIdEqualTo(userId);
		}
		if (date != null){
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),0,0,0);
			Date start = cal.getTime();
			cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),23,59,59);
			Date end = cal.getTime();
			criteria = criteria.andDateBetween(start,end);
		}
		if (message != null && !message.isEmpty()){
			criteria = criteria.andMessageEqualTo(message);
		}
		return mapper.selectByExample(example);
	}



   //////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
	public List<HistoryDialogueInfoBO> findAll(){
		HistoryDialogueInfoExample example=new HistoryDialogueInfoExample();
		return mapper.selectByExample(example);
	}
	List<HistoryDialogueInfoBO> findAll(Iterable<Long> ids){
		HistoryDialogueInfoExample example=new HistoryDialogueInfoExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		HistoryDialogueInfoExample example=new HistoryDialogueInfoExample();
		return mapper.countByExample(example);
	}

	public List<HistoryDialogueInfo> save(Iterable<HistoryDialogueInfo> entities){
		List<HistoryDialogueInfo> list=new ArrayList<HistoryDialogueInfo>();
		for (HistoryDialogueInfo HistoryDialogueInfo : entities) {
			list.add(save(HistoryDialogueInfo));
		}
		return list;
	}
	
	public HistoryDialogueInfo save(HistoryDialogueInfo record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(HistoryDialogueInfo record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public HistoryDialogueInfoBO findOne(Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(Long id){
		if(id == null){
			return false;
		}
		HistoryDialogueInfoExample example=new HistoryDialogueInfoExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(HistoryDialogueInfo entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<HistoryDialogueInfo> entities){
		List<Long> ids=Lists.newArrayList();
		for (HistoryDialogueInfo  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<Long> ids){
		HistoryDialogueInfoExample example=new HistoryDialogueInfoExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		HistoryDialogueInfoExample example=new HistoryDialogueInfoExample();
		mapper.deleteByExample(example);
	}
	//////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
}
