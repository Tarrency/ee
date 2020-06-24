package com.cusc.cuscai.dao;

import com.cusc.cuscai.entity.bo.AgentInfoBO;
import com.cusc.cuscai.entity.model.*;
import com.cusc.cuscai.mapper.AgentInfoMapper;
import com.cusc.cuscai.mapper.AgentMountKGMapper;
import com.cusc.cuscai.mapper.AgentMountQAMapper;
import com.cusc.cuscai.mapper.AgentMountWDMapper;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class AgentInfoDao {

	/**
	 * agent
	 */
	@Autowired
	private AgentInfoMapper mapper;
	/**
	 * 挂载到agent的QA
	 */
	@Autowired
	private AgentMountQAMapper QAmapper;
	/**
	 * 挂载到agent的KG
	 */
	@Autowired
	private AgentMountKGMapper KGmapper;
	/**
	 * 挂载到agent的WD
	 */
	@Autowired
	private AgentMountWDMapper WDmapper;
	/**
	 * 事务，联表查询需要用到
	 */
	@Autowired
	private PlatformTransactionManager transactionManager;

	/**
	 * 按adminId查找该管理员创建的所有agent
	 * @param adminId 管理员id
	 * @param agentName agent名称,可空。若为空，则返回全部数据。
	 * @return agent列表
	 */
	public List<AgentInfoBO> findByAdminAndName(java.lang.Integer adminId, String agentName) {
		AgentInfoExample example = new AgentInfoExample();
		AgentInfoExample.Criteria c = example.createCriteria().andAdminIdEqualTo(adminId);
		if (agentName != null && !agentName.isEmpty()) {
			c.andAgentNameLike(agentName);
		}
		return mapper.selectByExample(example);
	}

	/**
	 * 按adminId查找该管理员创建的给定agentId的agent
	 * 一遍来说List里面只有1个或0个数据，出现多个agent是bug
	 * @param adminId 管理员id
	 * @param agentId agent id
	 * @return agent列表
	 */
	public List<AgentInfoBO> findByAdminAndId(java.lang.Integer adminId, java.lang.Integer agentId) {
		AgentInfoExample example = new AgentInfoExample();
		example.createCriteria().andAdminIdEqualTo(adminId).andAgentIdEqualTo(agentId);
		return mapper.selectByExample(example);
	}

	@Transactional
	public Integer newAgent(java.lang.Integer adminID, String agentName,
			List<java.lang.Integer> QA_ids,
			List<java.lang.Integer> scene_ids,
			List<java.lang.Integer> voc_ids) {
		TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
		return transactionTemplate.execute(txStatus -> {
			AgentInfo agentInfo = new AgentInfo();
			agentInfo.setAdminId(adminID);
			agentInfo.setAgentName(agentName);
			agentInfo.setAgentCreateTime(new Date());
			agentInfo.setAgentUpdateTime(new Date());
			agentInfo.setAgentStatus(1);
			save(agentInfo);
			//写联表
			int agentId = agentInfo.getAgentId();
			//删除已有的
			AgentMountQAExample example=new AgentMountQAExample();
			example.createCriteria().andAgentIdEqualTo(agentId);
			QAmapper.deleteByExample(example);
			AgentMountKGExample example1=new AgentMountKGExample();
			example.createCriteria().andAgentIdEqualTo(agentId);
			KGmapper.deleteByExample(example1);
			AgentMountWDExample example2=new AgentMountWDExample();
			example.createCriteria().andAgentIdEqualTo(agentId);
			WDmapper.deleteByExample(example2);

			//插入新的
			for (Integer id :QA_ids){
				AgentMountQA agentMountQA = new AgentMountQA();
				agentMountQA.setAgentId(agentId);
				agentMountQA.setQaId(id);
				QAmapper.insert(agentMountQA);
			}
			for (Integer id :scene_ids){
				AgentMountKG agentMountKG = new AgentMountKG();
				agentMountKG.setAgentId(agentId);
				agentMountKG.setKgId(id);
				KGmapper.insert(agentMountKG);
			}
			for (Integer id :voc_ids){
				AgentMountWD agentMountWD = new AgentMountWD();
				agentMountWD.setAgentId(agentId);
				agentMountWD.setWdId(id);
				WDmapper.insert(agentMountWD);
			}
			return agentInfo.getAgentId();
		});
	}

	@Transactional
	public void changeAgent(java.lang.Integer adminID, java.lang.Integer agentID, String agentName,
			List<java.lang.Integer> QA_ids,
			List<java.lang.Integer> scene_ids,
			List<java.lang.Integer> voc_ids) {
		TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
		transactionTemplate.execute(txStatus -> {
			AgentInfo agentInfo = new AgentInfo();
			agentInfo.setAgentId(agentID);
			agentInfo.setAdminId(adminID);
			agentInfo.setAgentName(agentName);
			save(agentInfo);
			//写联表
			int agentId = agentInfo.getAgentId();
			//删除已有的
			AgentMountQAExample example=new AgentMountQAExample();
			example.createCriteria().andAgentIdEqualTo(agentId);
			QAmapper.deleteByExample(example);
			AgentMountKGExample example1=new AgentMountKGExample();
			example.createCriteria().andAgentIdEqualTo(agentId);
			KGmapper.deleteByExample(example1);
			AgentMountWDExample example2=new AgentMountWDExample();
			example.createCriteria().andAgentIdEqualTo(agentId);
			WDmapper.deleteByExample(example2);

			//插入新的
			for (Integer id :QA_ids){
				AgentMountQA agentMountQA = new AgentMountQA();
				agentMountQA.setAgentId(agentId);
				agentMountQA.setQaId(id);
				QAmapper.insert(agentMountQA);
			}
			for (Integer id :scene_ids){
				AgentMountKG agentMountKG = new AgentMountKG();
				agentMountKG.setAgentId(agentId);
				agentMountKG.setKgId(id);
				KGmapper.insert(agentMountKG);
			}
			for (Integer id :voc_ids){
				AgentMountWD agentMountWD = new AgentMountWD();
				agentMountWD.setAgentId(agentId);
				agentMountWD.setWdId(id);
				WDmapper.insert(agentMountWD);
			}
			return null;
		});
	}
   //////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
	public List<AgentInfoBO> findAll(){
		AgentInfoExample example=new AgentInfoExample();
		return mapper.selectByExample(example);
	}
	List<AgentInfoBO> findAll(Iterable<java.lang.Integer> ids){
		AgentInfoExample example=new AgentInfoExample();
		example.createCriteria().andAgentIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		AgentInfoExample example=new AgentInfoExample();
		return mapper.countByExample(example);
	}

	public List<AgentInfo> save(Iterable<AgentInfo> entities){
		List<AgentInfo> list=new ArrayList<AgentInfo>();
		for (AgentInfo AgentInfo : entities) {
			list.add(save(AgentInfo));
		}
		return list;
	}
	
	public AgentInfo save(AgentInfo record){
		if(!exists(record.getAgentId())){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(AgentInfo record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public AgentInfoBO findOne(java.lang.Integer id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Integer id){
		if(id == null){
			return false;
		}
		AgentInfoExample example=new AgentInfoExample();
		example.createCriteria().andAgentIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Integer id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Integer id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(AgentInfo entity){
		 mapper.deleteByPrimaryKey(entity.getAgentId());
	}

	public void delete(Iterable<AgentInfo> entities){
		List<java.lang.Integer> ids=Lists.newArrayList();
		for (AgentInfo  entity: entities) {
			ids.add(entity.getAgentId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Integer> ids){
		AgentInfoExample example=new AgentInfoExample();
		example.createCriteria().andAgentIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		AgentInfoExample example=new AgentInfoExample();
		mapper.deleteByExample(example);
	}
	//////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
}
