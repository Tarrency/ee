package com.cusc.cuscai.entity.kgEntity;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
@RelationshipEntity(type="relation")
public class Relation {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "name")
    private String relation;

//    @StartNode
//    private Base start;
//    @EndNode
//    private Base end;

    @StartNode
    private Base start;
    @EndNode
    private Base end;
}
