package com.cusc.cuscai.controller;
import com.alibaba.fastjson.JSONObject;
import com.cusc.cuscai.dto.GraphDTO;
import com.cusc.cuscai.entity.bo.KGDBinfoBO;
import com.cusc.cuscai.service.kgService.KGServer;
import com.cusc.cuscai.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.neo4j.ogm.response.model.NodeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Api(tags = "KG相关接口") //一个tag
@RestController
@RequestMapping("/kg")
public class KGController {
    @Autowired
    private KGServer kgServer;

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @ApiOperation("获取实体标签")
    @GetMapping("getEntLabels")
    public Result getEntLabels() {
        return Result.success("查询实体标签", kgServer.getEntLabels());
    }

    @ApiOperation("获取关系标签")
    @GetMapping("getRelLabels")
    public Result getRelLabels() {
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
    @ApiOperation("添加实体")
    @PostMapping("addNode")
    public Result addNode(@RequestBody @ApiParam(value = "json字符串", required = true) JSONObject propertyList) {
        Object node = kgServer.addNode(propertyList);
        return Result.success("添加实体成功", node);
    }

    @ApiOperation("查找节点")
    @PostMapping("/findNode")
    public Result findNodeByName(@RequestParam("name") @ApiParam(value = "name", required = true) String name) {
        System.out.println(name);
        List<Object> nodes = kgServer.findNodeByName(name);
        List<Map<String, Object>> res = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", ((NodeModel) nodes.get(i)).getId());
            map.put("type", ((NodeModel) nodes.get(i)).getLabels()[0]);
            for (int j = 0; j < ((NodeModel) nodes.get(i)).getPropertyList().size(); j++) {
                if (((NodeModel) nodes.get(i)).getPropertyList().get(j).getKey().equals("name")) {
                    map.put("name", ((NodeModel) nodes.get(i)).getPropertyList().get(j).getValue());
                }
            }
            res.add(map);
        }
        return Result.success("查找节点", res);
    }

    @ApiOperation("查找节点内容")
    @PostMapping("/findDetail")
    public Result findNodeByID(@RequestParam(value = "id") @ApiParam(value = "id", required = true) String id) {
        System.out.println(id);
        List<Map<String, Object>> res = new ArrayList<>();
        Object node = kgServer.findNodeByID(Long.parseLong(id));
        NodeModel n = (NodeModel) node;
        Map<String, Object> m = new HashMap<>();
        m.put("property", "id");
        m.put("content", n.getId());
        res.add(m);
        for (int i = 0; i < n.getPropertyList().size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("property", n.getPropertyList().get(i).getKey());
            map.put("content", n.getPropertyList().get(i).getValue());
            res.add(map);
        }
        return Result.success("查找节点内容", res);
    }

    @ApiOperation("查找邻居节点")
    @PostMapping("/findNerborByID")
    public Result findNeighbors(@RequestParam("id")
                                @ApiParam(value = "id", required = true) String id) {
        System.out.println(id);
        List<Map<String, Object>> res = kgServer.getNeighbors(Long.parseLong(id));
        return Result.success("查询邻居节点", res);
    }

    @ApiOperation("可视化")
    @PostMapping("/paint")
    public Result paint(@RequestParam("id")
                        @ApiParam(value = "id", required = true) String id) {
//        List neibor = kgServer.findNeiborByName(name);
        GraphDTO neibor = kgServer.paint(Long.parseLong(id));
        // System.out.println( ((Node) (neibor.getNodes().get(0))).get("properties").get("name"));
        // nodes.get(0).get("properties").get("name");
        return Result.success("查询邻居节点", neibor);
    }

    @ApiOperation("修改实体属性")
    @PostMapping("/modifyNode")
    public Result modifyNode(@RequestBody @ApiParam(value = "json字符串", required = true) JSONObject propertyList) {
        Integer id = (Integer) propertyList.get("id");
        System.out.println(id);
        propertyList.remove("id");
        Object modify = kgServer.modifyNode(id.longValue(), propertyList);
        return Result.success("修改实体成功", modify);
    }

    @ApiOperation("删除实体")
    @PostMapping("/deleteNode")
    public Result deleteNode(@RequestParam("id")
                             @ApiParam(value = "id", required = true) String id) {
        kgServer.deleteNode(Long.parseLong(id));
        return Result.success("删除成功", null);
    }


    @ApiOperation("添加关系")
    @PostMapping("/addRelation")
    public Result addRelation(@RequestParam("id1") @ApiParam(value = "id1", required = true) String id1,
                              @RequestParam("id2") @ApiParam(value = "id2", required = true) String id2,
                              @RequestParam("type") @ApiParam(value = "关系类型", required = true) String type) {
        String r = kgServer.addRelation(Long.parseLong(id1), Long.parseLong(id2), type);
        return Result.success("添加关系", r);
    }

    @ApiOperation("删除关系")
    @PostMapping("/deleteRelation")
    public Result deleteRelation(@RequestParam("id")
                                 @ApiParam(value = "id", required = true) String id) {
        kgServer.deleteRelation(Long.parseLong(id));
        return Result.success("删除成功", null);
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

    @PostMapping("/uploadTemplate")
    @ApiOperation("批量上传")
    public Result uploadExcel(@RequestParam("file") MultipartFile file, String type) {
        if (file == null || file.isEmpty()) {
            return Result.fail(500, "文件有误");
        }
        System.out.println(type);
        if (type.equals("entity")) {
            kgServer.getEntFromFile(file);
        } else {
            kgServer.getRelFromFile(file);
        }
        return Result.success("批量上传成功", null);
    }

    @GetMapping("/getDBlist")
    @ApiOperation("查询数据库列表信息")
    public Result getList() {

        List<KGDBinfoBO> dbList = new ArrayList<>();
        try {
            dbList = kgServer.findAll();
        } catch (Exception e) {
            return Result.fail(40601, e.getMessage());
        }
        return Result.success(20601, "查询数据库列表信息", dbList);
    }
    @PostMapping("/addDBInfo")
    @ApiOperation("添加数据库信息")
    public Result newAgent(
            @RequestParam("dbname") @ApiParam(value = "数据库名称", required = true) String dbname,
            @RequestParam("entities") @ApiParam(value = "实体数量", required = true) int entities,
            @RequestParam("relationships") @ApiParam(value = "关系数量", required = true) int relationships
    ) {
           kgServer.addDBInfo(dbname, entities, relationships);
       return Result.success(20602, "添加数据成功",null);
    }

}
