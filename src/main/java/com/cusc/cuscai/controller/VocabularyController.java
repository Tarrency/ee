package com.cusc.cuscai.controller;

import com.cusc.cuscai.entity.apibo.VocabularyBO;
import com.cusc.cuscai.entity.apibo.WordBO;
import com.cusc.cuscai.entity.bo.VocabularyInfoBO;
import com.cusc.cuscai.entity.bo.WordInfoBO;
import com.cusc.cuscai.entity.model.VocabularyInfo;
import com.cusc.cuscai.service.VocabularyService;
import com.cusc.cuscai.util.POIUtil;
import com.cusc.cuscai.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vocabulary")
@Api(tags = "词表相关接口")
public class VocabularyController {

    @Autowired
    private VocabularyService vocabularyService;


    @GetMapping("/getlist")
    @ResponseBody
    @ApiOperation("获取词表名称")
    public Result getList(@RequestParam("type") @ApiParam(value = "0专用词，1敏感词", required = true) int type) {

        List<VocabularyBO> resdata = new ArrayList<VocabularyBO>();
        try {
            resdata = vocabularyService.getVocabularyList(type);
        } catch (Exception e) {
            return Result.fail(40502, "查询词表失败", e.getMessage());
        }
        return Result.success(20502, "查询词表成功", resdata);
    }


    @PostMapping("/addnew")
    @ResponseBody
    @ApiOperation("新增词表")
    public Result newVocabulary(@RequestParam("name") @ApiParam(value = "词表名称", required = true) String name,
                                @RequestParam("type") @ApiParam(value = "词表类型", required = true) int type) {
        VocabularyInfo vocabularyInfo;
        try {
            vocabularyInfo = vocabularyService.newVocabulary(name, type);
        } catch (Exception e) {
            return Result.fail(40500, "新增词表失败", e.getMessage());
        }
        return Result.success(20500, "新增词表成功",vocabularyInfo);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    @ApiOperation("删除词表")
    public Result deleteVocabulary(@RequestParam("id") @ApiParam(value = "词表ID", required = true) int vcbid) {
        vocabularyService.deleteVocabulary(vcbid);
        return Result.success(20506, "删除词表成功");
    }


    @GetMapping("/search")
    @ResponseBody
    @ApiOperation("查询词汇")
    public Result searchWordInfo(@RequestParam("id") @ApiParam(value = "词表ID", required = true) int vcbid,
                                 @RequestParam(value = "key", required = false) @ApiParam(value = "关键词") String key) {
        List<WordBO> res = new ArrayList<WordBO>();
        try {
            res = vocabularyService.getWordList(vcbid, key);
        } catch (Exception e) {
            System.out.println(e);
            return Result.fail(40503, "查询词汇失败", e.getMessage());
        }
        return Result.success(20503, "查询词汇成功", res);
    }


    @PostMapping("/addword")
    @ResponseBody
    @ApiOperation("添加词汇")
    public Result addword(@RequestParam("id") @ApiParam(value = "词表ID", required = true) int vcbid,
                          @RequestParam("words") @ApiParam(value = "词语列表", required = true) List<String> words) {
        int insertcount;
        try {
            insertcount = vocabularyService.insertWords(vcbid, words);
        } catch (Exception e) {
            return Result.fail(40501, "添加词汇失败", e.getMessage());
        }

        return Result.success(20501, "添加词汇成功", insertcount);
    }

    @PostMapping("/modify")
    @ResponseBody
    @ApiOperation("修改词汇")
    public Result modifyWord(@RequestParam("wordID") @ApiParam(value = "词语id", required = true) int wordID,
                             @RequestParam("newWord") @ApiParam(value = "新词", required = true) String newWord) {
        vocabularyService.updateWord(wordID, newWord);
        return Result.success(20504, "修改词汇成功");
    }

    @DeleteMapping("/deleteWords")
    @ResponseBody
    @ApiOperation("删除词汇")
    public Result deleteWords(@RequestParam("wordIDs") @ApiParam(value = "词语列表", required = true) List<Integer> wordIDs) {
        vocabularyService.deleteWords(wordIDs);
        return Result.success(20505, "删除词汇成功");
    }

    @PostMapping("/upload")
    @ApiOperation(value = "批量添加词汇", notes = "根据词表ID，批量execl文件中添加词汇，每个词汇一个单元格，纵向排列")
    public Result uploadWords(@RequestParam("id") @ApiParam(value = "词表ID", required = true) int vcbid,
                              @RequestParam("file") @ApiParam(value = "上传的execl文件", required = true) MultipartFile uploadFile) {
        if (uploadFile == null || uploadFile.isEmpty()) {
            return Result.fail(400, "文件有误");
        }

        try {
            List<Map> datas = POIUtil.parseExcel(uploadFile);
            List<String> words = new ArrayList<>();
            String word;
            for (Map map : datas) {
                word = (String) map.get("word");
                if(!word.isEmpty()){
                    words.add(word);
                }
            }

            int insertcount;
            insertcount = vocabularyService.insertWords(vcbid, words);
            return Result.success(20501, "批量添加词汇成功", insertcount);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(40501, "批量添加词汇失败", e.getMessage());
        }
    }
}