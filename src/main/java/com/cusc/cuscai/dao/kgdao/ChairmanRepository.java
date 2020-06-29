package com.cusc.cuscai.dao.kgdao;

import com.cusc.cuscai.entity.kgEntity.Chairman;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChairmanRepository extends Neo4jRepository<Chairman, Long> {
    //通过名字 返回节点n以及n指向的所有节点与关系
    @Query("MATCH p=(m)-[r]->(n{name:{name}}) RETURN p")
    List<Chairman> findAllRel(@Param("name") String name);


    // @Query("MATCH (n{name:{name}}) RETURN n")

//    @Query("MATCH (a),(b) WHERE a.name = {name1} AND b.name = {name2} CREATE (a)-[r:relation{name:[{rela_name}]}]->(b) RETURN r")
//    List<Relation> addRelation(@Param("name1") String name1, @Param("name2")String name2, @Param("rela_name") String rela_name);
}
