package com.cusc.cuscai.dao;

import com.cusc.cuscai.entity.bo.KnowledgeInfoBO;
import com.cusc.cuscai.entity.model.KnowledgeBase;
import com.cusc.cuscai.entity.model.KnowledgeInfo;
import com.cusc.cuscai.entity.model.KnowledgeInfoExample;
import com.cusc.cuscai.entity.model.KnowledgeBaseExample;
import org.springframework.beans.factory.annotation.Autowired;
import com.cusc.cuscai.mapper.KnowledgeInfoMapper;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QAKnowledgeDao {

    @Autowired
    private KnowledgeInfoMapper mapper;

    public boolean exists(java.lang.Integer id){
        if(id == null){
            return false;
        }
        KnowledgeInfoExample example= new KnowledgeInfoExample();
        example.createCriteria().andKnowledgeIdEqualTo(id);
        return mapper.countByExample(example) > 0;
    }

    public KnowledgeBase saveKB(KnowledgeBase record){
        if(!exists(record.getKBID())){
            mapper.insertSelectiveKB(record);
        }else {
            mapper.updateByPrimaryKeySelectiveKB(record);
        }
        return record;
    }

    public int insertBatch(List<KnowledgeInfo> list){
        return mapper.insertBatch(list);
    }

    public void deleteByKIIds(Iterable<java.lang.Integer> ids){
        KnowledgeInfoExample example = new KnowledgeInfoExample();
        example.createCriteria().andWordIdIn(Lists.newArrayList(ids));
        mapper.deleteByKIExample(example);
    }

    public void deleteByKBIds(Iterable<java.lang.Integer> ids){
        KnowledgeBaseExample example = new KnowledgeBaseExample();
        example.createCriteria().andWordIdIn(Lists.newArrayList(ids));
        mapper.deleteByKBExample(example);
    }

    public List<KnowledgeInfoBO> findAll(String KBname){
        KnowledgeInfoExample example = new KnowledgeInfoExample();
        example.createCriteria().andKnowledgeBaseIdEqualTo(KBname);
        return mapper.selectByExample(example);
    }

    public List<KnowledgeInfoBO> findByKeywordandKBname(String keyword,String KBname){
        KnowledgeInfoExample example = new KnowledgeInfoExample();
        example.createCriteria().andKnowledgeBaseIdEqualTo(KBname).andWordLike('%'+keyword+'%');
        return mapper.selectByExample(example);
    }

    public KnowledgeInfo save(KnowledgeInfo record) {
        if(!exists(record.getKnowledgeId())){
            mapper.insertSelectiveKI(record);
        }else {
            mapper.updateByPrimaryKeySelectiveKI(record);
        }
        return record;
    }
}
