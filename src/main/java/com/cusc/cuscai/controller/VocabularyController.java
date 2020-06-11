package com.cusc.cuscai.controller;

import com.cusc.cuscai.entity.bo.VocabularyInfoBO;
import com.cusc.cuscai.entity.bo.WordInfoBO;
import com.cusc.cuscai.service.VocabularyService;
import com.cusc.cuscai.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vocabulary")
@Api(tags = "词表相关接口")
public class VocabularyController {

    @Autowired
    private VocabularyService vocabularyService;


    @GetMapping("/getlist")
    @ResponseBody
    @ApiOperation("获取词表名称")
    public Result getList(@RequestParam("词表类型") @ApiParam(value = "type", required = true) int type) {
        System.out.println("hello");
        List<VocabularyInfoBO> res = new ArrayList<VocabularyInfoBO>();
        try {
            res = vocabularyService.getVocabularyList(type);
        }catch (Exception e){
            System.out.println(e);
        }
        return Result.success(res);
    }



    @PostMapping("/addnew")
    @ResponseBody
    @ApiOperation("新增词表")
    public Result newVocabulary(@RequestParam("词表名称") @ApiParam(value = "name", required = true) String name,
                                @RequestParam("词表类型") @ApiParam(value = "type", required = true) int type) {
        vocabularyService.newVocabulary(name,type);
        return Result.success("新增词表成功");
    }

    @DeleteMapping("/delete")
    @ResponseBody
    @ApiOperation("删除词表")
    public Result deleteVocabulary(@RequestParam("词表ID") @ApiParam(value = "id", required = true) int vcbid) {
        vocabularyService.deleteVocabulary(vcbid);
        return Result.success("删除词表成功");
    }


    @GetMapping("/search")
    @ResponseBody
    @ApiOperation("查询词汇")
    public Result searchWordInfo(@RequestParam("词表ID") @ApiParam(value = "type", required = true) int vcbid,
                                 @RequestParam("关键词") @ApiParam(value = "key") String key) {
        List<WordInfoBO> res = vocabularyService.getWordList(vcbid,key);
        return Result.success(res);
    }


    @PostMapping("/addword")
    @ResponseBody
    @ApiOperation("添加词汇")
    public Result addword(@RequestParam("词表ID") @ApiParam(value = "type", required = true) int vcbid,
                          @RequestParam("词语列表")  @ApiParam(value = "words") List<String> words) {

        System.out.println(new java.util.Date()+"  "+words);
        int insertcount = vocabularyService.insertWords(vcbid,words);
        return Result.success("成功添加词汇"+insertcount+"条");
    }

    @PostMapping ("/modify")
    @ResponseBody
    @ApiOperation("修改词汇")
    public Result modifyWord(@RequestParam("词语id") @ApiParam(value = "wordID", required = true) int wordID,
                             @RequestParam("新词")  @ApiParam(value = "newWord") String newWord) {
        vocabularyService.updateWord(wordID,newWord);
        return Result.success("成功修改词汇");
    }

    @DeleteMapping("/deleteWords")
    @ResponseBody
    @ApiOperation("删除词汇")
    public Result deleteWords(@RequestParam("词语列表")  @ApiParam(value = "wordIDs") List<Integer> wordIDs) {
        vocabularyService.deleteWords(wordIDs);
        return Result.success("成功删除词汇");
    }


}
