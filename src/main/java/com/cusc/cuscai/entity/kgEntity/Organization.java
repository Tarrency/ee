package com.cusc.cuscai.entity.kgEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@Data
@NodeEntity(label = "组织")
@ApiModel("组织")
public class Organization{
    @Id
    @GeneratedValue
    @ApiModelProperty(value = "id")
    protected Long id;

    @Property(name = "name")
    @ApiModelProperty(value = "姓名")
    protected String name;

    @Property(name = "描述")
    @ApiModelProperty(value = "描述")
    private String desc;

    @Property(name="口号")
    @ApiModelProperty(value = "口号")
    private String slogan;

    @Property(name="愿景")
    @ApiModelProperty(value = "愿景")
    private String vision;

    @Property(name="使命")
    @ApiModelProperty(value = "使命")
    private String mission;

    @Property(name="核心价值观")
    @ApiModelProperty(value = "核心价值观")
    private String value;

    @Property(name="经营管理理念")
    @ApiModelProperty(value = "经营管理理念")
    private String idea;


}
