package com.cusc.cuscai.service;


import com.cusc.cuscai.dao.VocabularyInfoDao;
import com.cusc.cuscai.dao.WordInfoDao;
import com.cusc.cuscai.entity.apibo.VocabularyBO;
import com.cusc.cuscai.entity.apibo.WordBO;
import com.cusc.cuscai.entity.bo.VocabularyInfoBO;
import com.cusc.cuscai.entity.bo.WordInfoBO;
import com.cusc.cuscai.entity.model.VocabularyInfo;
import com.cusc.cuscai.entity.model.WordInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VocabularyService {


    @Autowired
    private VocabularyInfoDao vocabularydao ;
    @Autowired
    private WordInfoDao worddao;


    //获取词表详情
    public List<VocabularyBO> getVocabularyList(int type) {
        List<VocabularyInfoBO> resdata = new ArrayList<VocabularyInfoBO>();
        try {
            resdata = vocabularydao.findByType(type);
        }catch (Exception e){
            throw e;
        }
        List<VocabularyBO> resbo = new ArrayList<VocabularyBO>();
        for(VocabularyInfoBO bo : resdata){
            resbo.add(new VocabularyBO(bo));
        }
        return resbo;
    }


    //获取词汇信息
    public List<WordBO>  getWordList(int vcbid, String keyword) {
        List<WordBO> resbo= new ArrayList<WordBO>();
        List<WordInfoBO>  resdata= new ArrayList<WordInfoBO>();
        if(keyword == null || keyword.length() ==0)
            resdata = worddao.findByVocabularyID(vcbid);
        else
            resdata =  worddao.findByVocabularyIDandKeyword(vcbid,keyword);
        for(WordInfoBO bo :resdata){
            resbo.add(new WordBO(bo));
        }
        return resbo;
    }

    //新增词表
    public VocabularyInfo newVocabulary(String name, int type) {
        VocabularyInfo vcb = new VocabularyInfo();
        vcb.setName(name);
        vcb.setType(type);
        return vocabularydao.save(vcb);
    }

    //删除词表
    public void deleteVocabulary(int vcbid) {
        vocabularydao.delete(vcbid);
    }

    //插入单词
    public int insertWords(int vcbid, List<String> words) {
        List<WordInfo> wordlist = new ArrayList<WordInfo>();
        for(String wordstr:words){
            WordInfo word = new WordInfo(vcbid,wordstr);
            wordlist.add(word);
        }
        return worddao.insertBatch(wordlist);
    }

    //修改单词
    public void updateWord( int wordid,String word) {
        WordInfo wordinfo = new WordInfo();
        wordinfo.setWordId(wordid);
        wordinfo.setWord(word);
        worddao.save(wordinfo);
    }

    //删除单词
    public void deleteWords(List<Integer> wordIDs) {
        worddao.deleteByIds(wordIDs);
    }

}

