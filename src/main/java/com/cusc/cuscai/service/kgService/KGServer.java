package com.cusc.cuscai.service.kgService;

import com.cusc.cuscai.entity.kgEntity.Chairman;
import com.cusc.cuscai.entity.kgEntity.Organization;
import com.cusc.cuscai.entity.kgEntity.Person;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface KGServer {
    Person addNode(Person person);
    Organization addNode(Organization organization);
    Person findPersonById(long id);
    Organization findOrgById(long id);
    List findNeiborByName(String name);
    Person modifyPerson(long id, String name, String desc, String sex);
    void deletePersonById(long id);

    Chairman addChairman(String name1, String name2);

    String upload(MultipartFile file);

    List<Person> getPersonFromFile(MultipartFile file);
    List<Chairman> getChairmanFromFile(MultipartFile file);
}
