package com.cusc.cuscai.entity.kgEntity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Property;

@Data
public class Base {
    @Id
    @GeneratedValue
    @ApiModelProperty(value = "id", required = true)
    protected Long id;

    @Property(name = "name")
    @ApiModelProperty(value = "姓名", required = true)
    protected String name;

}
