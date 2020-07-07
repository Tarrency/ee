package com.cusc.cuscai.controller;


import com.alibaba.fastjson.JSONObject;
import com.cusc.cuscai.dto.GraphDTO;
import com.cusc.cuscai.entity.kgEntity.Chairman;
import com.cusc.cuscai.entity.kgEntity.Person;
import com.cusc.cuscai.service.kgService.KGServer;
import com.cusc.cuscai.util.JsonSimple;
import com.cusc.cuscai.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.neo4j.driver.types.Node;
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

    @ApiOperation("获取实体标签")
    @GetMapping("getEntLabels")
    public Result getEntLabels(){
        return Result.success("查询实体标签", kgServer.getEntLabels());
    }

    @ApiOperation("获取关系标签")
    @GetMapping("getRelLabels")
    public Result getRelLabels(){
        return Result.success("查询关系标签", kgServer.getRelLabels());
    }

    @ApiOperation("统计各类实体数量")
    @GetMapping("countEntities")
    public Result countEntities() {
        return Result.success("统计各类实体数量", kgServer.countEntities());
    }

    @ApiOperation("统计关系数量")
    @GetMapping("countRelations")
    public Result countRelations() {
        return Result.success("统计关系数量", kgServer.countRelations());
    }

    // 正确的前后端逻辑应该是 前端选择一个实体类型，后端返回给实体属性，然后前端再输入属性具体值发给后端进行添加
    // 这些属性都应该是字符串的形式，后期改
    @ApiOperation("添加实体")
    @PostMapping("addNode")
    public Result addNode(@RequestBody @ApiParam(value = "json字符串", required = true) JSONObject propertyList) {
        Object node = kgServer.addNode(propertyList);
        return Result.success("添加实体成功", node);
    }

    @ApiOperation("查找节点")
    @GetMapping("/findNode")
    public Result findNodeByName(@RequestParam("name") @ApiParam(value = "name", required = true) String name){
        return Result.success("查找节点", kgServer.findNodeByName(name));
    }

    @ApiOperation("查找邻居节点")
    @GetMapping("/findNerborByName")
    public Result findNeighbor(@RequestParam("name")
                                 @ApiParam(value = "name", required = true) String name){
//        List neibor = kgServer.findNeiborByName(name);
        GraphDTO neibor = kgServer.getNeibors(name);
        // System.out.println( ((Node) (neibor.getNodes().get(0))).get("properties").get("name"));
        // nodes.get(0).get("properties").get("name");
        System.out.println(JsonSimple.toJson(neibor));
        return Result.success("查询邻居节点", neibor);
    }

    @ApiOperation("修改实体属性")
    @PostMapping("/modifyNode")
    public Result modifyNode(@RequestBody @ApiParam(value = "json字符串", required = true) JSONObject propertyList){
        String id = (String) propertyList.get("id");
        Object modify = kgServer.modifyNode(Long.parseLong(id), propertyList);
        return Result.success("修改实体成功", modify);
    }

    @ApiOperation("删除实体")
    @DeleteMapping("/deleteNode")
    public Result deleteNode(@RequestParam("id")
                                       @ApiParam(value = "id", required = true) String id){
        kgServer.deleteNode(Long.parseLong(id));
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

//    @PostMapping("/uploadExcel")
//    @ApiOperation("批量上传Person节点")
//    public Result uploadExcel(@RequestParam("file") MultipartFile file) {
//        // 传来的参数应该为：节点类型 + 文件
//        // 根据节点类型选择上传的是哪类实体，判断调用对应service接口
//        // 这里只写person
//        if (file == null || file.isEmpty()) {
//            return Result.fail(500, "文件有误");
//        }
//        List<Person> persons = kgServer.getPersonFromFile(file);
//        for(Person p : persons){
//            kgServer.addNode(p);
//        }
//        return Result.success("批量上传成功", persons);
//    }

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
