package com.cusc.cuscai.entity.kgEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@Data
@NodeEntity(label = "人物")
@ApiModel("人物")
public class Person extends Base {

    @Property(name = "描述")
    @ApiModelProperty(value = "描述")
    private String desc;

    @Property(name = "人物")
    private String person;

    @Property(name="性别")
    private String sex;

    @Property(name = "别名")
    @ApiModelProperty(value = "别名")
    private String otherName;
}