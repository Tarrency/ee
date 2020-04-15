package com.cusc.cuscai.dao.kgdao;


import com.cusc.cuscai.entity.kgEntity.Base;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NodeRepository extends Neo4jRepository<Base, Long> {
    @Query("MATCH (n{name:{name}}) RETURN n")
    Base findByName(@Param("name") String name);

    @Query("MATCH (n{name:{name}})-[r:relation]->(m) RETURN m")
    List<Base> findNeighbor(@Param("name") String name);
}
