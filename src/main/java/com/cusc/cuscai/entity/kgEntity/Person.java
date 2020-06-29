package com.cusc.cuscai.entity.kgEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@Data
@NodeEntity(label = "人物")
@ApiModel("人物")
public class Person {
    @Id
    @GeneratedValue
    @ApiModelProperty(value = "id")
    protected Long id;

    @Property(name = "name")
    @ApiModelProperty(value = "姓名")
    private String name;

    @Property(name = "描述")
    @ApiModelProperty(value = "描述")
    private String desc;

    @Property(name="性别")
    private String sex;
}