package com.cusc.cuscai.dao.kgdao;

import com.cusc.cuscai.entity.kgEntity.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {
//    @Query("MATCH (n{name:{name}}) RETURN n")
    Person findByName(@Param("name") String name);

    @Query("MATCH (n{name:{name}})-[r]->(m) RETURN m UNION MATCH (m)-[r]->(n{name:{name}}) RETURN m")
    List<Person> findNeighbor(@Param("name") String name);
}
