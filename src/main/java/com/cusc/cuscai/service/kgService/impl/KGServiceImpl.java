package com.cusc.cuscai.service.kgService.impl;

import com.alibaba.fastjson.JSONObject;
import com.cusc.cuscai.dao.KGDBinfoDao;
import com.cusc.cuscai.dao.kgdao.*;
import com.cusc.cuscai.dto.GraphDTO;
import com.cusc.cuscai.entity.bo.KGDBinfoBO;
import com.cusc.cuscai.exception.GlobalException;
import com.cusc.cuscai.service.kgService.KGServer;
import com.cusc.cuscai.util.POIUtil;
import com.cusc.cuscai.util.UUIDUtil;
import org.neo4j.driver.internal.InternalPath;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;
import org.neo4j.ogm.response.model.NodeModel;
import org.neo4j.ogm.response.model.RelationshipModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.*;

@Service
public class KGServiceImpl implements KGServer {
    @Autowired
    Neo4jDao neo4jDao;

    @Autowired
    KGDBinfoDao kgdbinfoDao;

    /**
     * 实体的增删改查
     */

    // 增
    @Override
    public Object addNode(JSONObject propertyList) {
        List<Object> keys = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        String label = null;
        for(Object key: propertyList.keySet()){
            String str = "label";
            if(str.equals(key)){
                label = (String) propertyList.get("label");
                continue;
            }
            keys.add(key);
            values.add(propertyList.get(key));
        }
        Neo4jSession session = neo4jDao.open();
        String cypher = "create (n:`" + label + "` {" ;
        for(int i=0; i<keys.size(); i++) {
            Object key = keys.get(i);
            Object value = values.get(i);
            if(i == keys.size()-1) {
                cypher += "`" + key + "`:'" + value + "'}) return n";
            } else {
                cypher += "`" + key + "`:'" + value + "',";
            }
        }
        Iterator<Map<String, Object>> iterator = session.exec(cypher);
        Map<String, Object> each = iterator.next();
        Object addNode = each.get('n');
        return addNode;
    }

    // 查
    @Override
    public Object findNodeByID(long id){
        Neo4jSession session = neo4jDao.open();
        String cypher = "MATCH (n) where id(n) = " + id + " RETURN (n)";
        Iterator<Map<String, Object>> iterator = session.exec(cypher);
        Map<String, Object> each = iterator.next();
        Object node = each.get("n");
        return node;
    }

    @Override
    public List<Object> findNodeByName(String name) {
        Neo4jSession session = neo4jDao.open();
        String cypher = "MATCH (n) where n.name = '" + name + "' RETURN (n)";
        Iterator<Map<String, Object>> iterator = session.exec(cypher);
        List<Object> ans = new ArrayList<>();
        while(iterator.hasNext()) {
            Map<String, Object> each = iterator.next();
            NodeModel node = (NodeModel) each.get("n");
            ans.add(node);
        }
        return ans;
    }

    // 查询关联节点
    @Override
    public List<Map<String, Object>> getNeighbors(long id) {
        Neo4jSession session = neo4jDao.open();
        String cypher = " match (na)-[r]->(nb) where id(na) = " + id + " return r,nb";
        Iterator<Map<String, Object>> iterator = session.exec(cypher);
        List<Map<String, Object>> res = new ArrayList<>();
        while(iterator.hasNext()) {
            Map<String, Object> each = iterator.next();
            RelationshipModel r = (RelationshipModel) each.get("r");
            String rtype = r.getType();
            Long rid = r.getId();
            NodeModel node = (NodeModel) each.get("nb");
            long nb_id = node.getId();
            String name = "";
//            String name = (String) node.getPropertyList().get(0).getValue();
            for(int j=0; j<node.getPropertyList().size(); j++) {
                if(node.getPropertyList().get(j).getKey().equals("name")) {
                    name = (String) node.getPropertyList().get(j).getValue();
                }
            }
            Map<String, Object> map = new HashMap<>();
            map.put("relation", rtype);
            map.put("rid", rid);
            map.put("ids", nb_id);
            map.put("names", name);
            res.add(map);
        }
        return res;
    }

    // 改
    @Override
    public Object modifyNode(long id, JSONObject propertyList) {
        List<Object> keys = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        for(Object key: propertyList.keySet()){
            keys.add(key);
            values.add(propertyList.get(key));
        }
        Neo4jSession session = neo4jDao.open();
        String cypher = "MATCH (n) where id(n)=" + id;
        for(int i=0; i<keys.size(); i++) {
            Object key = keys.get(i);
            Object value = values.get(i);
            cypher += " SET n.`" + key + "`=" + "'" + value + "'";
        }
        cypher += " RETURN n";
        Iterator<Map<String, Object>> iterator = session.exec(cypher);
        Map<String, Object> each = iterator.next();
        Object modify = each.get("n");
        return modify;
    }

    // 删
    @Override
    public void deleteNode(long id){
        Neo4jSession session = neo4jDao.open();
        String cypher = "MATCH (n)-[r]-() where id(n)=" + id + " delete r";
        Iterator<Map<String, Object>> iterator = session.exec(cypher);
        cypher = "MATCH (n) where id(n)=" + id + " delete n";
        Iterator<Map<String, Object>> iterator2 = session.exec(cypher);
    }

    /**
     * 关系
     */

    @Override
    public String addRelation(long id1, long id2, String type) {
        Neo4jSession session = neo4jDao.open();
        String cypher = " match (na),(nb) where id(na) = " + id1 + " and id(nb) = " + id2 + " create (na)-[r:`" + type + "`]->(nb)" + " return r";
        Iterator<Map<String, Object>> iterator = session.exec(cypher);
        List<Map<String, Object>> res = new ArrayList<>();
        Map<String, Object> each = iterator.next();
        RelationshipModel r = (RelationshipModel) each.get("r");
        return r.getType();
    }

    @Override
    public void deleteRelation(long id) {
        Neo4jSession session = neo4jDao.open();
        String cypher = "MATCH ()-[r]-() where id(r)=" + id + " delete r";
        Iterator<Map<String, Object>> iterator = session.exec(cypher);
    }

    /**
     * 批量
     */
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
    public void getEntFromFile(MultipartFile file) {
        try {
            List<Map> list = POIUtil.parseExcel(file);
            transEnt(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(500, "服务器内部错误");
        }
    }

    public void transEnt(List<Map> list) {
        String label = null;
        Neo4jSession session = neo4jDao.open();
        for(Map map: list) {
            List<Object> keys = new ArrayList<>();
            List<Object> values = new ArrayList<>();
            for(Object key: map.keySet()){
                String str = "label";
                if(str.equals(key)){
                    label = (String) map.get("label");
                    continue;
                }
                keys.add(key);
                values.add(map.get(key));
            }
            String cypher = "create (n:`" + label + "` {" ;
            for(int i=0; i<keys.size(); i++) {
                Object key = keys.get(i);
                Object value = values.get(i);
                if(i == keys.size()-1) {
                    cypher += "`" + key + "`:'" + value + "'}) return n";
                } else {
                    cypher += "`" + key + "`:'" + value + "',";
                }
            }
            System.out.println(cypher);
            Iterator<Map<String, Object>> iterator = session.exec(cypher);
            Map<String, Object> each = iterator.next();
            Object addNode = each.get('n');
        }
    }

    @Override
    public void getRelFromFile(MultipartFile file) {
        try {
            List<Map> list = POIUtil.parseExcel(file);
            transRelation(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(500, "服务器内部错误");
        }
    }

    public void transRelation(List<Map> list) {
        Neo4jSession session = neo4jDao.open();
        for(Map map: list) {
            String cypher = " match (na),(nb) where id(na) = " + map.get("id1")  + " and id(nb) = " + map.get("id2") + " create (na)-[r:`" + map.get("type") + "`]->(nb)" + " return r";
            Iterator<Map<String, Object>> iterator = session.exec(cypher);
            List<Map<String, Object>> res = new ArrayList<>();
            Map<String, Object> each = iterator.next();
            RelationshipModel r = (RelationshipModel) each.get("r");
        }
    }

    /**
     * 统计功能
     */
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

    /**
     * 可视化
     */

    @Override
    public GraphDTO paint(long id) {
        Neo4jSession session = neo4jDao.open();
        String cypher = " match data=(na)-[r]->(nb) where id(na)=" + id + " return data";
        Iterator<Map<String, Object>> iterator = session.exec(cypher);
        GraphDTO gto = mapToGraph(iterator);
        System.out.println(gto.getNodes());
        System.out.println(gto.getLinks());
        return gto;
    }


    private GraphDTO mapToGraph(Iterator<Map<String, Object>> neo4jDataIterator) {
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
            Node start = (Node) internalPath.start();
            Node end = (Node) internalPath.end();
            nodeMap.put(start.id(), start);
            nodeMap.put(end.id(), end);

//            for (Node node : internalPath.nodes()) {
//                InternalNode internalNode = (InternalNode) node;
//                if (nodeMap.containsKey(internalNode.id())) {
//                    continue;
//                }
//                nodeMap.put(internalNode.id(), internalNode);
//            }

            //归纳出所有关系。 (InternalRelationship)?
            Relationship relation = internalPath.relationship();
            linkMap.put(relation.id(), relation);
//            for (Relationship relation : internalPath.relationships()) {
//                InternalRelationship internalRelation = (InternalRelationship) relation;
//                if (linkMap.containsKey(internalRelation.id())) {
//                    continue;
//                }
//                linkMap.put(internalRelation.id(), internalRelation);
//            }
        }
        List<Map<String, Object>> resN = new ArrayList<>();
        List<Map<String, Object>> resR = new ArrayList<>();
        for(Long id: nodeMap.keySet()) {
            Map<String, Object> one = new HashMap<>();
            Node n = (Node) nodeMap.get(id);
            one.put("id", id);
//            Iterable<String> labels = n.labels();
            one.put("label", n.labels().iterator().next());
            one.put("name", n.asMap().get("name"));
//            for(int i=0; i< n.().size(); i++) {
//                if(n.getPropertyList().get(i).getKey().equals("name")) {
//                    one.put("name", n.getPropertyList().get(i).getValue());
//                }
//            }
            resN.add(one);
        }

        for(Long id: linkMap.keySet()) {
            Map<String, Object> one = new HashMap<>();
            Relationship r = (Relationship) linkMap.get(id);
            one.put("id", id);
            one.put("type", r.type());
            one.put("start", r.startNodeId());
            one.put("end", r.endNodeId());
            resR.add(one);
        }

        GraphDTO dto = new GraphDTO();
        dto.setNodes(Collections.singletonList(resN));
        dto.setLinks(Collections.singletonList(resR));
//        dto.setNodes(nodeMap.values().stream().collect(Collectors.toList()));
//        dto.setLinks(linkMap.values().stream().collect(Collectors.toList()));
        return dto;
    }

    /**
     * 数据库操作
     */
    @Override
    public List<KGDBinfoBO> findAll() {
        return kgdbinfoDao.findAll() ;
    }
}
