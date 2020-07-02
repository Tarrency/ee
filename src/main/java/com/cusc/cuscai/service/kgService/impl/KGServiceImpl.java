package com.cusc.cuscai.service.kgService.impl;

import com.cusc.cuscai.dao.kgdao.*;
import com.cusc.cuscai.dto.GraphDTO;
import com.cusc.cuscai.entity.kgEntity.*;
import com.cusc.cuscai.exception.GlobalException;
import com.cusc.cuscai.service.kgService.KGServer;
import com.cusc.cuscai.util.JsonSimple;
import com.cusc.cuscai.util.POIUtil;
import com.cusc.cuscai.util.UUIDUtil;
import org.neo4j.driver.internal.InternalPath;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class KGServiceImpl implements KGServer {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    ChairmanRepository chairmanRepository;
    @Autowired
    PresidentRepository presidentRepository;

    @Autowired
    Neo4jDao neo4jDao;

    /**
    注意 添加和修改节点都需要区分是那种节点。因为有不同的属性。上传同样如此，上传需要区分节点类型。
     */

    @Override
    public Person addNode(Person person){ //添加Person节点
        return personRepository.save(person);
    }

    @Override
    public Organization addNode(Organization org){ //添加Person节点
        return organizationRepository.save(org);
    }

    @Override
    public Person findPersonById(long id){
        Person p = personRepository.findById(id).get();
        System.out.println(JsonSimple.toJson(p));
        return p;
    }

    @Override
    public Person findPersonByName(String name){
        Person p = personRepository.findByName(name);
        System.out.println(JsonSimple.toJson(p));
        return p;
    }

    @Override
    public Organization findOrgById(long id){
        Organization o = organizationRepository.findById(id).get();
        System.out.println(JsonSimple.toJson(o));
        return o;
    }

    @Override
    public List findNeiborByName(String name){
//        Person node = personRepository.findByName(name); //节点自己
//        System.out.println(JsonSimple.toJson(node));
        // 目前只映射了两类实体 ，故查询在这两类实体中的邻居节点；后续改
        List<Person> plist = personRepository.findNeighbor(name);
        List<Organization> olist = organizationRepository.findNeighbor(name);
        List res = new ArrayList();
        res.add(plist);
        res.add(olist);
        return res;
    }

    @Override
    public Person modifyPerson(long id, String name, String desc, String sex) { //修改Person节点
        Person person = findPersonById(id);
        if(!name.equals("") && name!=null && !name.equals("null")){
            person.setName(name);
        }
        if(!desc.equals("") && desc!=null && !desc.equals("null")){
            person.setDesc(desc);
        }
        if(!sex.equals("") && sex!=null && !sex.equals("null")){
            person.setSex(sex);
        }
        System.out.println(person);
        return personRepository.save(person);
    }

    @Override
    public void deletePersonById(long id){
        personRepository.deleteById(id);
    }

    @Override
    public Chairman addChairman(String name1, String name2) {
        Organization n1 = organizationRepository.findByName(name1);
        Person n2 = personRepository.findByName(name2);
        Chairman relation = new Chairman();
        relation.setStart(n1);
        relation.setEnd(n2);
        return chairmanRepository.save(relation);
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
    public List<Chairman> getChairmanFromFile(MultipartFile file) {
        List<Chairman> res = null;
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
            res.add(person);
        }
        return res;
    }

    public List<Chairman> transRelation(List<Map> list) {
        List<Chairman> res = new ArrayList<>();
        for (Map map : list) {
            Chairman relation = new Chairman();
            Organization node1 = organizationRepository.findByName((String) map.get("name1"));
            Person node2 = personRepository.findByName((String) map.get("name2"));
            relation.setStart(node1);
            relation.setEnd(node2);
            res.add(relation);
        }
        return res;
    }

    @Override
    public List<String> getEntLabels(){
        Neo4jSession session = neo4jDao.open();
        String cypher = "MATCH (n) RETURN distinct labels(n)";
        Iterator<Map<String, Object>> iterator = session.exec(cypher);
        List<String> ans = new ArrayList<>();
        while(iterator.hasNext()) {
            Map<String, Object> each = iterator.next();
            for(String s: each.keySet()) {
                String[] res = (String[]) each.get(s);
//                System.out.println(res[0]);
                ans.add(res[0]);
            }
        }
        return ans;
    }

    @Override
    public List<Map<String, Long>> countEntities() {
        Neo4jSession session = neo4jDao.open();
        String cypher = "MATCH (n) RETURN distinct labels(n), count(*)";
        Iterator<Map<String, Object>> iterator = session.exec(cypher);
        List<Map<String, Long>> ans = new ArrayList<>();
        while(iterator.hasNext()) {
            Map<String, Object> each = iterator.next();
            Map<String, Long> map = new HashMap<String, Long>();
            String[] label = (String[]) each.get("labels(n)");
            Long count = (Long) each.get("count(*)");
            map.put(label[0], count);
            ans.add(map);
        }
        return ans;
    }

    @Override
    public Long countRelations() {
        Neo4jSession session = neo4jDao.open();
        String cypher = "MATCH ()-[r]->() return count(r)";
        Iterator<Map<String, Object>> iterator = session.exec(cypher);
        Map<String, Object> each = iterator.next();
        Long count = (Long) each.get("count(r)");
        return count;
    }

    @Override
    public List<String> getRelLabels(){
        Neo4jSession session = neo4jDao.open();
        String cypher = "match ()-[r]->() return distinct type(r)";
        Iterator<Map<String, Object>> iterator = session.exec(cypher);
        List<String> ans = new ArrayList<>();
        while(iterator.hasNext()) {
            Map<String, Object> each = iterator.next();
            for(String s: each.keySet()) {
                String res = (String) each.get(s);
//                System.out.println(res[0]);
                ans.add(res);
            }
        }
        return ans;
    }

    @Override
    public GraphDTO getNeibors(String name) {
        Neo4jSession session = neo4jDao.open();
        String cypher = " match data=(na{name:'" + name + "'})-[r]->(nb) return data";
        Iterator<Map<String, Object>> iterator = session.exec(cypher);
        GraphDTO gto = mapToGraph(iterator);
        System.out.println(gto.getNodes());
        System.out.println(gto.getLinks());
        return gto;
    }

    private GraphDTO mapToGraph(
            Iterator<Map<String, Object>> neo4jDataIterator) {
        Map<Long, Object> nodeMap = new HashMap<>();
        Map<Long, Object> linkMap = new HashMap<>();

        while (neo4jDataIterator.hasNext()) {
            Map<String, Object> each = neo4jDataIterator.next();
            if (!each.containsKey("data")) {
                continue;
            }

            InternalPath.SelfContainedSegment[] internalPaths = (InternalPath.SelfContainedSegment[]) each.get("data");
            InternalPath.SelfContainedSegment internalPath = internalPaths[0];

            //归纳出所有节点。(InternalNode)?
            Node start =  internalPath.start();
            Node end = internalPath.end();
            nodeMap.put(start.id(), start.asMap().get("name"));
            nodeMap.put(end.id(), end.asMap().get("name"));

//            for (Node node : internalPath.nodes()) {
//                InternalNode internalNode = (InternalNode) node;
//                if (nodeMap.containsKey(internalNode.id())) {
//                    continue;
//                }
//                nodeMap.put(internalNode.id(), internalNode);
//            }

            //归纳出所有关系。 (InternalRelationship)?
            Relationship relation = internalPath.relationship();
            linkMap.put(relation.id(), relation.type());
//            for (Relationship relation : internalPath.relationships()) {
//                InternalRelationship internalRelation = (InternalRelationship) relation;
//                if (linkMap.containsKey(internalRelation.id())) {
//                    continue;
//                }
//                linkMap.put(internalRelation.id(), internalRelation);
//            }
        }

        GraphDTO dto = new GraphDTO();
        dto.setNodes(nodeMap.values().stream().collect(Collectors.toList()));
        dto.setLinks(linkMap.values().stream().collect(Collectors.toList()));
        return dto;
    }
}
