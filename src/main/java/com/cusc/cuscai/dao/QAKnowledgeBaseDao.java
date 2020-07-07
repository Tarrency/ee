package com.cusc.cuscai.dao;

import com.cusc.cuscai.entity.bo.KnowledgeBaseBO;
import com.cusc.cuscai.entity.model.KnowledgeBase;
import com.cusc.cuscai.entity.model.KnowledgeBaseExample;
import com.cusc.cuscai.mapper.KnowledgeBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QAKnowledgeBaseDao {

    @Autowired
    private KnowledgeBaseMapper mapper;

    public boolean exists(java.lang.Integer id){
        if(id == null){
            return false;
        }
        KnowledgeBaseExample example= new KnowledgeBaseExample();
        example.createCriteria().andKnowledgeBaseIdEqualTo(id);
        return mapper.countByExample(example) > 0;
    }

    public KnowledgeBase saveKB(KnowledgeBase record){
        if(!exists(record.getKBID())){
            mapper.insertSelective(record);
        }else {
            mapper.updateByPrimaryKeySelective(record);
        }
        return record;
    }

    public void delete(java.lang.Integer id){
        mapper.deleteByPrimaryKey(id);
    }

    public List<KnowledgeBaseBO> findAll(){
        KnowledgeBaseExample example = new KnowledgeBaseExample();
        return mapper.selectByExample(example);
    }
}
