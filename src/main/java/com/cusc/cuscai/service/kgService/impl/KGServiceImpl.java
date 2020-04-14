package com.cusc.cuscai.service.kgService.impl;

import com.cusc.cuscai.kgdao.NodeRepository;
import com.cusc.cuscai.kgdao.RelationRepository;
import com.cusc.cuscai.entity.kgEntity.Base;
import com.cusc.cuscai.entity.kgEntity.Person;
import com.cusc.cuscai.entity.kgEntity.Relation;
import com.cusc.cuscai.exception.GlobalException;
import com.cusc.cuscai.service.kgService.KGServer;
import com.cusc.cuscai.util.JsonSimple;
import com.cusc.cuscai.util.POIUtil;
import com.cusc.cuscai.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class KGServiceImpl implements KGServer {
    @Autowired
    NodeRepository nodeRepository;
    @Autowired
    RelationRepository relationRepository;

    /**
    注意 添加和修改节点都需要区分是那种节点。因为有不同的属性。上传同样如此，上传需要区分节点类型。
     */

    @Override
    public Base addNode(Person person){ //添加Person节点
        return nodeRepository.save(person);
    }

    @Override
    public Base findNodeById(long id){ //通用
        Base base = nodeRepository.findById(id).get();
        System.out.println(JsonSimple.toJson(base));
        return base;
    }

    @Override
    public List findNodeByName(String name){ //查自身信息及关联节点信息
        Base node = nodeRepository.findByName(name); //节点自己
        System.out.println(JsonSimple.toJson(node));
        List<Relation> list = relationRepository.findAllRel(name); //节点-关系-节点
        List res = new ArrayList();
        res.add(node);
        res.add(list);
        return res;
    }

    @Override
    public Base modifyNode(long id, String name, String desc, String sex) { //修改Person节点
        Base base = findNodeById(id);
        if(!name.equals("") && name!=null){
            base.setName(name);
        }
        if(!desc.equals("") && desc!=null){
            ((Person)base).setDesc(desc);
        }
        if(!sex.equals("") && sex!=null){
            ((Person)base).setSex(sex);
        }
        System.out.println(base);
        return nodeRepository.save(base);
    }

    @Override
    public void deleteNodeById(long id){
        nodeRepository.deleteById(id);
    }


    @Override
    public Relation addRelation(String name1, String name2, String rela_name) {
        Base n1=nodeRepository.findByName(name1);
        Base n2=nodeRepository.findByName(name2);
        Relation relation = new Relation();
        relation.setStart(n1);
        relation.setEnd(n2);
        relation.setRelation(rela_name);
        return relationRepository.save(relation);
    }

    @Override
    public String upload(MultipartFile file) { //上传
        String originName = file.getOriginalFilename();
        System.out.println(originName);
        String filename = UUIDUtil.genId() + originName.substring(originName.lastIndexOf('.'));
        System.out.println(filename);

        try {
            String path = ResourceUtils.getURL("classpath:").getPath();
            path = URLDecoder.decode(path, "UTF-8"); //解决中文乱码问题
            File newFile = new File(path + "/static/" + filename);
            System.out.println(path);
            if (!newFile.exists()) {
                newFile.mkdirs();
            }
            file.transferTo(newFile);
        } catch (Exception e) {
            System.out.println(e);
            throw new GlobalException(500, "文件错误");
        }
        return "http://localhost:8080/static/" + filename;
    }

    @Override
    public List<Person> getPersonFromFile(MultipartFile file) {
        List<Person> res = null;
        try {
            List<Map> list = POIUtil.parseExcel(file);
            res = transPerson(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(500, "服务器内部错误");
        }
        return res;
    }

    @Override
    public List<Relation> getRelationFromFile(MultipartFile file) {
        List<Relation> res = null;
        try {
            List<Map> list = POIUtil.parseExcel(file);
            res = transRelation(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(500, "服务器内部错误");
        }
        return res;
    }

    public List<Person> transPerson(List<Map> list) {
        List<Person> res = new ArrayList<>();
        for (Map map : list) {
            Person person = new Person();
            person.setName((String) map.get("name"));
            person.setDesc((String) map.get("desc"));
            person.setSex((String) map.get("sex"));
            person.setOtherName((String) map.get("otherName"));
            person.setPerson(person.getName());
            res.add(person);
        }
        return res;
    }

    public List<Relation> transRelation(List<Map> list) {
        List<Relation> res = new ArrayList<>();
        for (Map map : list) {
            Relation relation = new Relation();
            Base node1 = nodeRepository.findByName((String) map.get("name1"));
            Base node2 = nodeRepository.findByName((String) map.get("name2"));
            relation.setStart(node1);
            relation.setEnd(node2);
            relation.setRelation((String) map.get("relation"));
            res.add(relation);
        }
        return res;
    }
}
