package com.cusc.cuscai.dao;

import java.util.ArrayList;
import java.util.List;

import com.cusc.cuscai.entity.bo.WordInfoBO;
import com.cusc.cuscai.entity.model.WordInfo;
import com.cusc.cuscai.entity.model.WordInfoExample;
import com.cusc.cuscai.mapper.WordInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;



@Repository
public class WordInfoDao {

	@Autowired
	WordInfoMapper mapper;

   //////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
	public List<WordInfoBO> findAll(){
		WordInfoExample example=new WordInfoExample();
		return mapper.selectByExample(example);
	}
	List<WordInfoBO> findAll(Iterable<java.lang.Integer> ids){
		WordInfoExample example=new WordInfoExample();
		example.createCriteria().andWordIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		WordInfoExample example=new WordInfoExample();
		return mapper.countByExample(example);
	}

	public List<WordInfo> save(Iterable<WordInfo> entities){
		List<WordInfo> list=new ArrayList<WordInfo>();
		for (WordInfo WordInfo : entities) {
			list.add(save(WordInfo));
		}
		return list;
	}
	
	public WordInfo save(WordInfo record){
		if(!exists(record.getWordId())){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(WordInfo record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public WordInfoBO findOne(java.lang.Integer id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Integer id){
		if(id == null){
			return false;
		}
		WordInfoExample example=new WordInfoExample();
		example.createCriteria().andWordIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Integer id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Integer id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(WordInfo entity){
		 mapper.deleteByPrimaryKey(entity.getWordId());
	}

	public void delete(Iterable<WordInfo> entities){
		List<java.lang.Integer> ids=Lists.newArrayList();
		for (WordInfo  entity: entities) {
			ids.add(entity.getWordId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Integer> ids){
		WordInfoExample example=new WordInfoExample();
		example.createCriteria().andWordIdIn(Lists.newArrayList(ids));
		mapper.deleteByExample(example);
	}

	public void deleteAll(){
		WordInfoExample example=new WordInfoExample();
		mapper.deleteByExample(example);
	}
	//////////////////////////////////////////////////////
   ///** generate code begin 此标记中间不要添加自定义代码 **///
   //////////////////////////////////////////////////////
	public List<WordInfoBO> findByVocabularyID(int vcbid){
		WordInfoExample example=new WordInfoExample();
		example.createCriteria().andVocabularyIdEqualTo(vcbid);
		return mapper.selectByExample(example);
	}

	public List<WordInfoBO> findByVocabularyIDandKeyword(int vcbid,String keyword){
		WordInfoExample example=new WordInfoExample();
		example.createCriteria().andVocabularyIdEqualTo(vcbid).andWordLike('%'+keyword+'%');
		return mapper.selectByExample(example);
	}

	public int insertBatch(List<WordInfo> list){
		return mapper.insertBatch(list);
	}

	public List<WordInfoBO> findByVocabularyIDs(List<Integer> vcbids){
		WordInfoExample example=new WordInfoExample();
		example.createCriteria().andVocabularyIdIn(vcbids);
		return mapper.selectByExample(example);
	}
}
