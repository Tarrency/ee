package com.cusc.cuscai.controller;


import com.cusc.cuscai.entity.kgEntity.Base;
import com.cusc.cuscai.entity.kgEntity.Person;
import com.cusc.cuscai.entity.kgEntity.Relation;
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
public class KGController {
    @Autowired
    private KGServer kgServer;

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    @ApiOperation("添加person")
    @PostMapping("addPerson")
    public Result addPerson(@RequestParam("姓名") @ApiParam(value = "name", required = true) String name,
                            @RequestParam("描述") @ApiParam(value = "born") String desc,
                            @RequestParam("性别") @ApiParam(value = "sex") String sex,
                            @RequestParam("别名") @ApiParam(value = "otherName") String otherName) {
        Person person = new Person();
        person.setPerson(name);
        person.setName(name);
        if(!desc.equals("") && desc!=null){
            person.setDesc(desc);
        }
        if(!sex.equals("") && sex!=null){
            person.setSex(sex);
        }
        if(!otherName.equals("") && otherName!=null){
            person.setOtherName(otherName);
        }
        kgServer.addNode(person);
        return Result.success("添加person成功", person);
    }

    @ApiOperation("查找节点")
    @GetMapping("/findNodeById")
    public Base findNodeById(@RequestParam("id")
                                     @ApiParam(value = "id", required = true) String id){
        return kgServer.findNodeById(Long.parseLong(id));
    }

    @ApiOperation("查找节点 by name")
    @GetMapping("/findNodeByName")
    public List findNodeByName(@RequestParam("name")
                                 @ApiParam(value = "name", required = true) String name){
        List person = kgServer.findNodeByName(name);
        System.out.println(JsonSimple.toJson(person));
        return person;
    }

    @ApiOperation("修改person属性")
    @GetMapping("/modifypersonbyId")
    public Result modifypersonbyId(@RequestParam("id") @ApiParam(value = "id", required = true) String id,
                                      @RequestParam("修改：姓名") @ApiParam(value = "name") String name,
                                      @RequestParam("修改：描述") @ApiParam(value = "desc") String desc,
                                      @RequestParam("修改：性别") @ApiParam(value = "sex") String sex){
        System.out.println(name+desc+sex);
        Base modify = kgServer.modifyNode(Long.parseLong(id), name, desc, sex);
        return Result.success("修改person成功", modify);
    }

    @ApiOperation("删除person")
    @DeleteMapping("/deletepersonbyId")
    public Result deletepersonbyId(@RequestParam("id")
                                       @ApiParam(value = "id", required = true) String id){
        kgServer.deleteNodeById(Long.parseLong(id));
        return Result.success("删除成功",null);
    }


    @ApiOperation("添加关系")
    @PostMapping("/addRelation")
    public Relation addRelation(@RequestParam("name1") @ApiParam(value = "name1", required = true) String name1,
                                @RequestParam("name2") @ApiParam(value = "name2", required = true) String name2,
                                @RequestParam("relation") @ApiParam(value = "relation", required = true) String relationship){
        return kgServer.addRelation(name1, name2, relationship);
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
    @ApiOperation("批量上传节点")
    public Result uploadExcel(@RequestParam("file") MultipartFile file) {
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
    @ApiOperation("批量导入关系")
    public Result uploadExcel2(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return Result.fail(500, "文件有误");
        }
        List<Relation> relations = kgServer.getRelationFromFile(file);
        for(Relation r : relations){
            kgServer.addRelation(r.getStart().getName(), r.getEnd().getName(), r.getRelation());
        }
        return Result.success("批量上传成功", relations);
    }
}
