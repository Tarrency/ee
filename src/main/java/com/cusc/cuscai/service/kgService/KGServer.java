package com.cusc.cuscai.service.kgService;

import com.alibaba.fastjson.JSONObject;
import com.cusc.cuscai.dto.GraphDTO;
import com.cusc.cuscai.entity.kgEntity.Chairman;
import com.cusc.cuscai.entity.kgEntity.Organization;
import com.cusc.cuscai.entity.kgEntity.Person;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface KGServer {
    // 实体
    Object addNode(JSONObject propertyList);
    Object findNodeByID(long id);
    List<Object> findNodeByName(String name);
    Object modifyNode(long id, JSONObject propertyList);
    void deleteNode(long id);
    List<Map<String, Object>> getNeighbors(long id);

    // 关系
    String addRelation(long id1, long id2, String type);


    // 批量
    String upload(MultipartFile file);
//
//    List<Person> getPersonFromFile(MultipartFile file);
//    List<Chairman> getChairmanFromFile(MultipartFile file);

    // 统计
    List<String> getEntLabels();
    List<Map<String, Long>> countEntities();
    Long countRelations();
    List<String> getRelLabels();

    // 可视化用
    GraphDTO getNeibors(String name);

}
