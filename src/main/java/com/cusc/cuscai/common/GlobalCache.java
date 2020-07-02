package com.cusc.cuscai.common;

import com.cusc.cuscai.dao.VocabularyInfoDao;
import com.cusc.cuscai.dao.WordInfoDao;
import com.cusc.cuscai.entity.bo.VocabularyInfoBO;
import com.cusc.cuscai.entity.bo.WordInfoBO;
import com.cusc.cuscai.util.sensitivewd.SensitiveFilter;
import jodd.madvoc.meta.In;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 全局变量
 */
@Component
public class GlobalCache {

    //敏感词过滤器
    public static SensitiveFilter sensitiveFilter = new SensitiveFilter();

    @Autowired
    private VocabularyInfoDao vocabularyInfoDao;

    @Autowired
    private WordInfoDao wordInfoDao;

    @PostConstruct
    public void init() {
        System.out.println("系统启动中...");
        System.out.println("初始化全局变量...");
        System.out.println("初始化敏感词过滤器...");
        List<VocabularyInfoBO> sensitiveVocabulary = vocabularyInfoDao.findByType(1);
        List<Integer> vcbids = new ArrayList<>();
        for (VocabularyInfoBO v: sensitiveVocabulary) {
            vcbids.add(v.getVocabularyId());
        }

        List<WordInfoBO> wordInfoBOs = wordInfoDao.findByVocabularyIDs(vcbids);

        for (WordInfoBO w: wordInfoBOs){
            sensitiveFilter.put(w.getWord());
        }
        System.out.println(String.format("初始化敏感词过滤器成功，载入%d个敏感词汇",wordInfoBOs.size()));
        System.out.println("初始化全局变量成功");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("退出系统");
    }
}
