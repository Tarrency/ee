package com.cusc.cuscai.entity.kgEntity;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
@RelationshipEntity(type="董事长")
public class Chairman {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Organization start;
    @EndNode
    private Person end;
}
