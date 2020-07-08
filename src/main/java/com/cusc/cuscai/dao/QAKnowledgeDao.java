package com.cusc.cuscai.dao;

import com.cusc.cuscai.entity.bo.KnowledgeBaseBO;
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

    public List<KnowledgeInfoBO> findByKeyword(String keyword){
        KnowledgeInfoExample example = new KnowledgeInfoExample();
        example.createCriteria().andWordLike('%' + keyword + '%');
        return mapper.selectByKIExample(example);
    }

    public List<KnowledgeInfoBO> findByKeywordAndKB(String keyword,String KBName){
        KnowledgeInfoExample example = new KnowledgeInfoExample();
        example.createCriteria().andWordLike('%' + keyword + '%');
        return mapper.selectByKIExample(example);
    }

    public List<KnowledgeInfoBO> findByKBID(int ID){
        KnowledgeInfoExample example = new KnowledgeInfoExample();
        example.createCriteria().andBaseIdEqualTo(ID);
        return mapper.selectByKIExample(example);
    }

    public boolean exists(java.lang.Integer id){
        if(id == null){
            return false;
        }
        KnowledgeInfoExample example= new KnowledgeInfoExample();
        example.createCriteria().andKnowledgeIdEqualTo(id);
        return mapper.countByExample(example) > 0;
    }

    public int insertBatch(List<KnowledgeInfo> list){
        return mapper.insertBatch(list);
    }

    public void deleteByKIIds(Iterable<java.lang.Integer> ids){
        KnowledgeInfoExample example = new KnowledgeInfoExample();
        example.createCriteria().andWordIdIn(Lists.newArrayList(ids));
        mapper.deleteByKIExample(example);
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
