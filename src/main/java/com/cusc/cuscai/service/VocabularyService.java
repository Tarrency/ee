package com.cusc.cuscai.service;


import com.cusc.cuscai.dao.VocabularyInfoDao;
import com.cusc.cuscai.dao.WordInfoDao;
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
    public List<VocabularyInfoBO> getVocabularyList(int type) {
        return vocabularydao.findByType(type);
    }

    //获取词汇信息
    public List<WordInfoBO> getWordList(int vcbid, String keyword) {
        if(keyword == null || keyword.length()==0)
           return worddao.findByVocabularyID(vcbid);
        else
            return worddao.findByVocabularyIDandKeyword(vcbid,keyword);
    }

    //新增词表
    public void newVocabulary(String name, int type) {
        VocabularyInfo vcb = new VocabularyInfo();
        vcb.setName(name);
        vcb.setType(type);
        vocabularydao.save(vcb);
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

