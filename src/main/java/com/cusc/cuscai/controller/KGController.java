package com.cusc.cuscai.controller;


import com.cusc.cuscai.entity.kgEntity.Chairman;
import com.cusc.cuscai.entity.kgEntity.Person;
import com.cusc.cuscai.service.kgService.KGServer;
import com.cusc.cuscai.util.JsonSimple;
import com.cusc.cuscai.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(tags = "KG相关接口") //一个tag
@RestController
@RequestMapping("/kg")
public class KGController {
    @Autowired
    private KGServer kgServer;

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    // 正确的前后端逻辑应该是 前端选择一个实体类型，后端返回给实体属性，然后前端再输入属性具体值发给后端进行添加
    // 这些属性都应该是字符串的形式，后期改
    @ApiOperation("添加person")
    @PostMapping("addPerson")
    public Result addPerson(@RequestParam("姓名") @ApiParam(value = "name", required = true) String name,
                            @RequestParam("描述") @ApiParam(value = "born") String desc,
                            @RequestParam("性别") @ApiParam(value = "sex") String sex) {
        Person person = new Person();
        person.setName(name);
        if(!desc.equals("") && desc!=null && !desc.equals("null")){
            person.setDesc(desc);
        }
        if(!sex.equals("") && sex!=null && !sex.equals("null")){
            person.setSex(sex);
        }
        kgServer.addNode(person);
        return Result.success("添加person成功", person);
    }

    @ApiOperation("查找人物节点")
    @GetMapping("/findPersonById")
    public Person findNodeById(@RequestParam("id")
                                     @ApiParam(value = "id", required = true) String id){
        return kgServer.findPersonById(Long.parseLong(id));
    }

    @ApiOperation("查找邻居节点")
    @GetMapping("/findNerborByName")
    public Result findNodeByName(@RequestParam("name")
                                 @ApiParam(value = "name", required = true) String name){
        List neibor = kgServer.findNeiborByName(name);
        System.out.println(JsonSimple.toJson(neibor));
        return Result.success("查询邻居节点", neibor);
    }

    @ApiOperation("修改person属性")
    @GetMapping("/modifypersonbyId")
    public Result modifypersonbyId(@RequestParam("id") @ApiParam(value = "id", required = true) String id,
                                      @RequestParam("修改：姓名") @ApiParam(value = "name") String name,
                                      @RequestParam("修改：描述") @ApiParam(value = "desc") String desc,
                                      @RequestParam("修改：性别") @ApiParam(value = "sex") String sex){
        System.out.println(name+desc+sex);
        Person modify = kgServer.modifyPerson(Long.parseLong(id), name, desc, sex);
        return Result.success("修改person成功", modify);
    }

    @ApiOperation("删除person")
    @DeleteMapping("/deletepersonbyId")
    public Result deletepersonbyId(@RequestParam("id")
                                       @ApiParam(value = "id", required = true) String id){
        kgServer.deletePersonById(Long.parseLong(id));
        return Result.success("删除成功",null);
    }


    @ApiOperation("添加chairman关系")
    @PostMapping("/addChairman")
    public Result addRelation(@RequestParam("name1") @ApiParam(value = "name1", required = true) String name1,
                                   @RequestParam("name2") @ApiParam(value = "name2", required = true) String name2){
        Chairman new_relation = kgServer.addChairman(name1, name2);
        return Result.success("添加chairman关系成功", new_relation) ;
    }

    @PostMapping("/upload")
    @ApiOperation("上传并保存文件")
    public Result upload(@RequestParam("file")
                            @ApiParam(value = "上传的文件", required = true) MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return Result.fail(500, "文件有误");
        }

        String url = kgServer.upload(file);
        return Result.success("文件上传成功", url);
    }

    @PostMapping("/uploadExcel")
    @ApiOperation("批量上传Person节点")
    public Result uploadExcel(@RequestParam("file") MultipartFile file) {
        // 传来的参数应该为：节点类型 + 文件
        // 根据节点类型选择上传的是哪类实体，判断调用对应service接口
        // 这里只写person
        if (file == null || file.isEmpty()) {
            return Result.fail(500, "文件有误");
        }
        List<Person> persons = kgServer.getPersonFromFile(file);
        for(Person p : persons){
            kgServer.addNode(p);
        }
        return Result.success("批量上传成功", persons);
    }

    @PostMapping("/uploadExcel2")
    @ApiOperation("批量导入Chairman关系")
    public Result uploadExcel2(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return Result.fail(500, "文件有误");
        }
        List<Chairman> relations = kgServer.getChairmanFromFile(file);
        for(Chairman r : relations){
            kgServer.addChairman(r.getStart().getName(), r.getEnd().getName());
        }
        return Result.success("批量上传成功", relations);
    }
}
