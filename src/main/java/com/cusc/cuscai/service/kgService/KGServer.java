package com.cusc.cuscai.service.kgService;

import com.cusc.cuscai.entity.kgEntity.Base;
import com.cusc.cuscai.entity.kgEntity.Person;
import com.cusc.cuscai.entity.kgEntity.Relation;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface KGServer {
    Base addNode(Person person);
    Base findNodeById(long id);
    List findNodeByName(String name);
    Base modifyNode(long id, String name, String desc, String sex);
    void deleteNodeById(long id);

    Relation addRelation(String name1, String name2, String rela_name);

    String upload(MultipartFile file);

    List<Person> getPersonFromFile(MultipartFile file);
    List<Relation> getRelationFromFile(MultipartFile file);
}
