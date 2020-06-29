package com.cusc.cuscai.entity.kgEntity;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
@RelationshipEntity(type="总经理")
public class President {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Organization start;
    @EndNode
    private Person end;
}
