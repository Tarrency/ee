package com.cusc.cuscai.dao;

import com.cusc.cuscai.entity.apibo.AgentModelBO;
import com.cusc.cuscai.entity.bo.*;
import com.cusc.cuscai.entity.model.*;
import com.cusc.cuscai.mapper.*;
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
     * 挂载到agent的QA知识库
     */
    @Autowired
    private AgentMountQAMapper QAmapper;
    /**
     * 挂载到agent的MR多轮对话
     */
    @Autowired
    private AgentMountMRMapper MRmapper;
    /**
     * 挂载到agent的KG知识图谱
     */
    @Autowired
    private AgentMountKGMapper KGmapper;
    /**
     * 挂载到agent的WD词表
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

    /**
     *
     * @param adminID 管理员id
     * @param agentName agent名字
     * @param modelType 模型类型
     * @param modelIds 模型id
     * @return 数据库创建新的 agent 所自动生成的 agent id
     */
    @Transactional
    public Integer newAgent(
            Integer adminID,
            String agentName,
            Integer modelType,
            List<String> modelIds
    ) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        return transactionTemplate.execute(txStatus -> {
            AgentInfo agentInfo = new AgentInfo();
            agentInfo.setAdminId(adminID);
            agentInfo.setAgentName(agentName);
            agentInfo.setAgentCreateTime(new Date());
            agentInfo.setAgentUpdateTime(new Date());
            agentInfo.setAgentStatus(1);
            save(agentInfo);

            //保存并重构关系
            int agentId = agentInfo.getAgentId();
            recreateRelationByAgentId(agentId, modelType, modelIds);
            return agentId;
        });
    }

    /**
     *
     * @param adminID 管理员id
     * @param agentID agent id
     * @param agentName agent名字
     * @param modelType 模型类型
     * @param modelIds 模型id
     */
    @Transactional
    public void changeAgent(
            Integer adminID,
            Integer agentID,
            String agentName,
            Integer modelType,
            List<String> modelIds
    ) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.execute(txStatus -> {
            AgentInfo agentInfo = new AgentInfo();
            agentInfo.setAgentId(agentID);
            agentInfo.setAdminId(adminID);
            agentInfo.setAgentName(agentName);
            agentInfo.setAgentUpdateTime(new Date());
            save(agentInfo);

            //保存并重构关系
            int agentId = agentInfo.getAgentId();
            recreateRelationByAgentId(agentId, modelType, modelIds);
            return null;
        });
    }

    /**
     * 重建关于agent的关系表
     * @param agentId 主键
     * @param modelType 模型类型
     * @param modelIds 模型id
     */
    private void recreateRelationByAgentId(
            int agentId,
            Integer modelType,
            List<String> modelIds
    ) {
        //已有的全部删除
        AgentMountQAExample qaExample = new AgentMountQAExample();
        qaExample.createCriteria().andAgentIdEqualTo(agentId);
        QAmapper.deleteByExample(qaExample);
        AgentMountKGExample kgExample = new AgentMountKGExample();
        kgExample.createCriteria().andAgentIdEqualTo(agentId);
        KGmapper.deleteByExample(kgExample);
        AgentMountMRExample mrExample = new AgentMountMRExample();
        mrExample.createCriteria().andAgentIdEqualTo(agentId);
        MRmapper.deleteByExample(mrExample);
        AgentMountWDExample wdExample = new AgentMountWDExample();
        wdExample.createCriteria().andAgentIdEqualTo(agentId);
        WDmapper.deleteByExample(wdExample);

        //只插入指定的model
        switch (modelType) {
            case 0:
                //QA知识库
                for (String id : modelIds) {
                    AgentMountQA agentMountQA = new AgentMountQA();
                    agentMountQA.setAgentId(agentId);
                    agentMountQA.setQaId(id);
                    QAmapper.insert(agentMountQA);
                }
                break;
            case 1:
                //知识图谱
                for (String id : modelIds) {
                    AgentMountKG agentMountKG = new AgentMountKG();
                    agentMountKG.setAgentId(agentId);
                    agentMountKG.setKgId(id);
                    KGmapper.insert(agentMountKG);
                }
                break;
            case 2:
                //多轮对话
                for (String id : modelIds) {
                    AgentMountMR agentMountMR = new AgentMountMR();
                    agentMountMR.setAgentId(agentId);
                    agentMountMR.setMrId(id);
                    MRmapper.insert(agentMountMR);
                }
                break;
            case 3:
                //词表
                for (String id : modelIds) {
                    AgentMountWD agentMountWD = new AgentMountWD();
                    agentMountWD.setAgentId(agentId);
                    agentMountWD.setWdId(id);
                    WDmapper.insert(agentMountWD);
                }
                break;
        }
    }

    /**
     * 搜索挂载到agent的所有模型（根据agent的类型）模型只有一种modelType
     * @param agentID agent id
     * @return {modelType:Int, modelIds:List<String>}
     */
    public AgentModelBO searchAgentModels(int agentID) {
        AgentInfo agentInfo = findOne(agentID);
        if (agentInfo == null) { return null; }
        int modelType = agentInfo.getModelType();
        List<String> modelIds = new ArrayList<>();
        switch (modelType) {
            case 0:
                //QA知识库
                AgentMountQAExample qaExample = new AgentMountQAExample();
                qaExample.createCriteria().andAgentIdEqualTo(agentID);
                List<AgentMountQABO> qaboList = QAmapper.selectByExample(qaExample);
                for (AgentMountQABO qabo : qaboList) {
                    modelIds.add(qabo.getQaId());
                }
                break;
            case 1:
                //知识图谱
                AgentMountKGExample kgExample = new AgentMountKGExample();
                kgExample.createCriteria().andAgentIdEqualTo(agentID);
                List<AgentMountKGBO> kgboList = KGmapper.selectByExample(kgExample);
                for (AgentMountKGBO kgbo : kgboList) {
                    modelIds.add(kgbo.getKgId());
                }
                break;
            case 2:
                //多轮对话
                AgentMountMRExample mrExample = new AgentMountMRExample();
                mrExample.createCriteria().andAgentIdEqualTo(agentID);
                List<AgentMountMRBO> mrboList = MRmapper.selectByExample(mrExample);
                for (AgentMountMRBO mrbo : mrboList) {
                    modelIds.add(mrbo.getMrId());
                }
                break;
            case 3:
                //词表
                AgentMountWDExample wdExample = new AgentMountWDExample();
                wdExample.createCriteria().andAgentIdEqualTo(agentID);
                List<AgentMountWDBO> wdboList = WDmapper.selectByExample(wdExample);
                for (AgentMountWDBO wdbo : wdboList) {
                    modelIds.add(wdbo.getWdId());
                }
                break;
        }
        return new AgentModelBO(modelType, modelIds);
    }

    public int delete(Integer adminID, Integer agentID) {
        AgentInfoExample example = new AgentInfoExample();
        example.createCriteria().andAdminIdEqualTo(adminID).andAgentIdEqualTo(agentID);
        return mapper.deleteByExample(example);
    }

    //////////////////////////////////////////////////////
    ///** generate code begin 此标记中间不要添加自定义代码 **///
    //////////////////////////////////////////////////////
    public List<AgentInfoBO> findAll() {
        AgentInfoExample example = new AgentInfoExample();
        return mapper.selectByExample(example);
    }

    List<AgentInfoBO> findAll(Iterable<java.lang.Integer> ids) {
        AgentInfoExample example = new AgentInfoExample();
        example.createCriteria().andAgentIdIn(Lists.newArrayList(ids));
        return mapper.selectByExample(example);
    }

    public long count() {
        AgentInfoExample example = new AgentInfoExample();
        return mapper.countByExample(example);
    }

    public List<AgentInfo> save(Iterable<AgentInfo> entities) {
        List<AgentInfo> list = new ArrayList<AgentInfo>();
        for (AgentInfo AgentInfo : entities) {
            list.add(save(AgentInfo));
        }
        return list;
    }

    public AgentInfo save(AgentInfo record) {
        if (!exists(record.getAgentId())) {
            mapper.insertSelective(record);
        } else {
            mapper.updateByPrimaryKeySelective(record);
        }
        return record;
    }


    public void update(AgentInfo record) {
        mapper.updateByPrimaryKeySelective(record);
    }

    public AgentInfoBO findOne(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public boolean exists(Integer id) {
        if (id == null) {
            return false;
        }
        AgentInfoExample example = new AgentInfoExample();
        example.createCriteria().andAgentIdEqualTo(id);
        return mapper.countByExample(example) > 0;
    }

    public void delete(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void remove(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void delete(AgentInfo entity) {
        mapper.deleteByPrimaryKey(entity.getAgentId());
    }

    public void delete(Iterable<AgentInfo> entities) {
        List<Integer> ids = Lists.newArrayList();
        for (AgentInfo entity : entities) {
            ids.add(entity.getAgentId());
        }
        deleteByIds(ids);
    }

    public void deleteByIds(Iterable<Integer> ids) {
        AgentInfoExample example = new AgentInfoExample();
        example.createCriteria().andAgentIdIn(Lists.newArrayList(ids));
        mapper.deleteByExample(example);
    }

    public void deleteAll() {
        AgentInfoExample example = new AgentInfoExample();
        mapper.deleteByExample(example);
    }
    //////////////////////////////////////////////////////
    ///** generate code begin 此标记中间不要添加自定义代码 **///
    //////////////////////////////////////////////////////
}
