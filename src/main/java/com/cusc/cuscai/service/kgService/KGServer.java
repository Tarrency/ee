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
//    Person addNode(Person person);
//    Organization addNode(Organization organization);
    Object addNode(JSONObject propertyList);
    Object findNodeByID(long id);
    List<Object> findNodeByName(String name);
    Object modifyNode(long id, JSONObject propertyList);
    void deleteNode(long id);

    Chairman addChairman(String name1, String name2);

    String upload(MultipartFile file);

    List<Person> getPersonFromFile(MultipartFile file);
    List<Chairman> getChairmanFromFile(MultipartFile file);

    List<String> getEntLabels();
    List<Map<String, Long>> countEntities();

    Long countRelations();
    List<String> getRelLabels();

    GraphDTO getNeibors(String name);
}
