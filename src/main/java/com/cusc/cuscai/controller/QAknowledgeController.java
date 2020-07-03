package com.cusc.cuscai.controller;


import com.cusc.cuscai.entity.bo.KnowledgeInfoBO;
import com.cusc.cuscai.service.QAknowledgeService;
import com.cusc.cuscai.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "QA知识库相关接口") //一个tag
@RestController
@RequestMapping("/QAknowledge")
public class QAknowledgeController {
    @Autowired
    private QAknowledgeService qAknowledgeService;

    @PostMapping("/create")
    @ResponseBody
    @ApiOperation("新建QA知识库")
    public Result newKnowledge(@RequestParam("QAKBName") @ApiParam(value = "新增的知识库名称", required = true) String QAKBName) {
        try {
            qAknowledgeService.newKB(QAKBName);
        } catch (Exception e) {
            return Result.fail(40200, e.getMessage());
        }
        return Result.success("新增QA知识库成功");
    }

    @PostMapping("/addKnowledge")
    @ResponseBody
    @ApiOperation("导入知识")
    public Result addKnowledge(@RequestParam("KBID") @ApiParam(value = "知识库id", required = true) Integer KBID,
                               @RequestParam("knowledge")  @ApiParam(value = "导入库中的知识", required = true) List<String> knowledge){
        int insertcount;
        try {
            insertcount = qAknowledgeService.insertKnowledge(KBID,knowledge);
        }catch (Exception e){
            return Result.fail(40201, e.getMessage());
        }
        return Result.success("知识导入成功");
    }

    @GetMapping("/getKnowledge")
    @ResponseBody
    @ApiOperation("默认查询QA知识库")
    public Result getQAKnowledge(@RequestParam("queryKey")  @ApiParam(value = "问答关键字", required = true) String queryKey,
                                 @RequestParam("queryName")  @ApiParam(value = "知识库名", required = true) String queryName){
        List<KnowledgeInfoBO> res= new ArrayList<KnowledgeInfoBO>();
        try{
            res=qAknowledgeService.getKnowledgeList(queryKey,queryName);
        }catch(Exception e){
            System.out.println(e);
            return Result.fail(40203,e.getMessage());
        }
        return Result.success(res);
    }

    @GetMapping("/getlist")
    @ResponseBody
    @ApiOperation("查询知识")
    public Result getKnowledge(@RequestParam("queryKey")  @ApiParam(value = "问答关键字", required = true) String queryKey,
                               @RequestParam("queryName")  @ApiParam(value = "知识库名", required = true) String queryName){
        List<KnowledgeInfoBO> res= new ArrayList<KnowledgeInfoBO>();
        try{
            res=qAknowledgeService.getKnowledgeList(queryKey,queryName);
        }catch(Exception e){
            System.out.println(e);
            return Result.fail(40203,e.getMessage());
        }
        return Result.success(res);
    }

    @PostMapping("/updateKnowledge")
    @ResponseBody
    @ApiOperation("修改知识")
    public Result updateKnowledge(@RequestParam("Id") @ApiParam(value = "知识id", required = true) int Id,
                                  @RequestParam("newQuestion")  @ApiParam(value = "新问题", required = true) String newQuestion,
                                  @RequestParam("newAnswer")  @ApiParam(value = "新答案", required = true) String newAnswer,
                                  @RequestParam("newType")  @ApiParam(value = "新类型", required = true) String newType){
        qAknowledgeService.updateKnowledge(Id,newQuestion,newAnswer,newType);
        return Result.success("成功修改知识");
    }

    @DeleteMapping("/deleteBase")
    @ResponseBody
    @ApiOperation("删除QA知识库")
        public Result deleteKnowledgeBase(@RequestParam("ids")  @ApiParam(value = "知识库列表", required = true) List<Integer> ids){
        qAknowledgeService.deleteKnowledgeBase(ids);
        return Result.success("成功删除知识库");
    }

    @DeleteMapping("/deleteKnowledge")
    @ResponseBody
    @ApiOperation("删除QA知识")
    public Result deleteKnowledge(@RequestParam("Ids")  @ApiParam(value = "词语列表", required = true) List<Integer> Ids){
        qAknowledgeService.deleteKnowledge(Ids);
        return Result.success("成功删除知识");
    }

}
