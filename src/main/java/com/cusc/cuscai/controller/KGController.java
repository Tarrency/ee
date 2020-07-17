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

    @ApiOperation("获取实体标签")
    @GetMapping("getEntLabels")
    public Result getEntLabels() {
        List<String> res;
        try {
            res = kgServer.getEntLabels();
        } catch (Exception e) {
            return Result.fail(40400, e.getMessage());
        }
        return Result.success(20400,"查询实体标签", res);
    }

//    @ApiOperation("获取关系标签")
//    @GetMapping("getRelLabels")
//    public Result getRelLabels() {
//        return Result.success("查询关系标签", kgServer.getRelLabels());
//    }
//
//    @ApiOperation("统计各类实体数量")
//    @GetMapping("countEntities")
//    public Result countEntities() {
//        return Result.success("统计各类实体数量", kgServer.countEntities());
//    }
//
//    @ApiOperation("统计关系数量")
//    @GetMapping("countRelations")
//    public Result countRelations() {
//        return Result.success("统计关系数量", kgServer.countRelations());
//    }

    /**
     * @param propertyList 属性列表，格式为属性名:属性值
     * @return
     */
    @ApiOperation("添加实体")
    @PostMapping("addNode")
    public Result addNode(@RequestBody @ApiParam(value = "json字符串", required = true) JSONObject propertyList) {
        Object node;
        try{
            node = kgServer.addNode(propertyList);
        } catch (Exception e) {
            return Result.fail(40401, e.getMessage());
        }
        return Result.success(20401,"添加实体成功", node);
    }

    /**
     *
     * @param name
     * @return
     */
    @ApiOperation("查找节点")
    @PostMapping("/findNode")
    public Result findNodeByName(@RequestParam("name") @ApiParam(value = "name", required = true) String name) {
        System.out.println(name);
        List<Object> nodes;
        List<Map<String, Object>> res = new ArrayList<>();
        try{
            nodes = kgServer.findNodeByName(name);
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
        } catch (Exception e) {
            return Result.fail(40402, e.getMessage());
        }
        return Result.success(20402,"查找节点", res);
    }

    /**
     *
     * @param id
     * @return
     */
    @ApiOperation("查找节点内容")
    @PostMapping("/findDetail")
    public Result findNodeByID(@RequestParam(value = "id") @ApiParam(value = "id", required = true) String id) {
        System.out.println(id);
        List<Map<String, Object>> res = new ArrayList<>();
        Object node;
        try{
            node = kgServer.findNodeByID(Long.parseLong(id));
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
        } catch (Exception e) {
            return Result.fail(40403, e.getMessage());
        }

        return Result.success(20403,"查找节点内容", res);
    }

    /**
     *
     * @param id
     * @return
     */
    @ApiOperation("查找邻居节点")
    @PostMapping("/findNerborByID")
    public Result findNeighbors(@RequestParam("id")
                                @ApiParam(value = "id", required = true) String id) {
        System.out.println(id);
        List<Map<String, Object>> res;
        try{
            res = kgServer.getNeighbors(Long.parseLong(id));
        } catch (Exception e) {
            return Result.fail(40404, e.getMessage());
        }
        return Result.success(20404,"查询邻居节点", res);
    }

    /**
     *
     * @param propertyList
     * @return
     */
    @ApiOperation("修改实体属性")
    @PostMapping("/modifyNode")
    public Result modifyNode(@RequestBody @ApiParam(value = "json字符串", required = true) JSONObject propertyList) {
        Integer id = (Integer) propertyList.get("id");
        System.out.println(id);
        propertyList.remove("id");
        Object modify;
        try{
            modify = kgServer.modifyNode(id.longValue(), propertyList);
        } catch (Exception e) {
            return Result.fail(40405, e.getMessage());
        }

        return Result.success(20405,"修改实体成功", modify);
    }

    /**
     *
     * @param id
     * @return
     */
    @ApiOperation("删除实体")
    @PostMapping("/deleteNode")
    public Result deleteNode(@RequestParam("id")
                             @ApiParam(value = "id", required = true) String id) {
        try {
            kgServer.deleteNode(Long.parseLong(id));
        } catch (Exception e) {
            return Result.fail(40406, e.getMessage());
        }
        return Result.success(20406,"删除成功", null);
    }

    /**
     *
     * @param id1
     * @param id2
     * @param type
     * @return
     */
    @ApiOperation("添加关系")
    @PostMapping("/addRelation")
    public Result addRelation(@RequestParam("id1") @ApiParam(value = "id1", required = true) String id1,
                              @RequestParam("id2") @ApiParam(value = "id2", required = true) String id2,
                              @RequestParam("type") @ApiParam(value = "关系类型", required = true) String type) {
        String r;
        try {
            r = kgServer.addRelation(Long.parseLong(id1), Long.parseLong(id2), type);
        } catch (Exception e) {
            return Result.fail(40407, e.getMessage());
        }
        return Result.success(20407,"添加关系", r);
    }

    /**
     *
     * @param id
     * @return
     */
    @ApiOperation("删除关系")
    @PostMapping("/deleteRelation")
    public Result deleteRelation(@RequestParam("id")
                                 @ApiParam(value = "id", required = true) String id) {
        try {
            kgServer.deleteRelation(Long.parseLong(id));
        } catch (Exception e) {
            return Result.fail(40408, e.getMessage());
        }
        return Result.success(20408,"删除成功", null);
    }

//    @PostMapping("/upload")
//    @ApiOperation("上传并保存文件")
//    public Result upload(@RequestParam("file")
//                             @ApiParam(value = "上传的文件", required = true) MultipartFile file) {
//        if (file == null || file.isEmpty()) {
//            return Result.fail(500, "文件有误");
//        }
//
//        String url = kgServer.upload(file);
//        return Result.success("文件上传成功", url);
//    }

    /**
     *
     * @param file
     * @param type：entity为批量上传实体，relation为批量上传关系
     * @return
     */
    @PostMapping("/uploadTemplate")
    @ApiOperation("批量上传")
    public Result uploadExcel(@RequestParam("file") MultipartFile file, String type) {
        if (file == null || file.isEmpty()) {
            return Result.fail(500, "文件有误");
        }
        System.out.println(type);
        try{
            if (type.equals("entity")) {
                kgServer.getEntFromFile(file);
            } else {
                kgServer.getRelFromFile(file);
            }
        } catch (Exception e) {
            return Result.fail(40409, e.getMessage());
        }
        return Result.success(20409,"批量上传成功", null);
    }

    /**
     *
     * @param id
     * @return
     */
    @ApiOperation("可视化")
    @PostMapping("/paint")
    public Result paint(@RequestParam("id")
                        @ApiParam(value = "id", required = true) String id) {
        GraphDTO neibor;
        try{
            neibor = kgServer.paint(Long.parseLong(id));
        } catch (Exception e) {
            return Result.fail(40410, e.getMessage());
        }
        return Result.success(20410,"查询邻居节点", neibor);
    }


    @GetMapping("/getDBlist")
    @ApiOperation("查询数据库列表信息")
    public Result getList() {

        List<KGDBinfoBO> dbList = new ArrayList<>();
        try {
            dbList = kgServer.findAll();
        } catch (Exception e) {
            return Result.fail(40411, e.getMessage());
        }
        return Result.success(20410, "查询数据库列表信息", dbList);
    }

    @PostMapping("/addDBInfo")
    @ApiOperation("添加数据库信息")
    public Result newAgent(
            @RequestParam("dbname") @ApiParam(value = "数据库名称", required = true) String dbname,
            @RequestParam("entities") @ApiParam(value = "实体数量", required = true) int entities,
            @RequestParam("relationships") @ApiParam(value = "关系数量", required = true) int relationships
    ) {
        try {
            kgServer.addDBInfo(dbname, entities, relationships);
        } catch (Exception e) {
            return Result.fail(40412, e.getMessage());
        }

       return Result.success(20412, "添加数据成功",null);
    }

    @ApiOperation("导出实体")
    @PostMapping("/downloadEntity")
    public Result downloadEntity() {
        String res;
        try{
            res = kgServer.getEntityFile();
        } catch (Exception e) {
            return Result.fail(40413, e.getMessage());
        }
        return Result.success(20413,"导出实体", res);
    }

    @ApiOperation("导出关系")
    @PostMapping("/downloadRelation")
    public Result downloadRelation() {
        String res;
        try{
            res = kgServer.getRelationFile();
        } catch (Exception e) {
            return Result.fail(40414, e.getMessage());
        }
        return Result.success(20414,"导出关系", res);
    }
}
