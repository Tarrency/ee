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
public class Organization extends Base {
    @Id
    @GeneratedValue
    @ApiModelProperty(value = "id", required = true)
    protected Long id;

    @Property(name = "name")
    @ApiModelProperty(value = "姓名", required = true)
    protected String name;

    @Property(name = "描述")
    @ApiModelProperty(value = "描述")
    private String desc;

    @Property(name = "别名")
    @ApiModelProperty(value = "别名")
    private String otherName;

    @Property(name="口号")
    @ApiModelProperty(value = "口号")
    private String slogan;

    @Property(name="愿景")
    @ApiModelProperty(value = "愿景")
    private String vision;

    @Property(name="使命")
    @ApiModelProperty(value = "使命")
    private String mission;

    @Property(name="价值观")
    @ApiModelProperty(value = "价值观")
    private String value;

    @Property(name="理念")
    @ApiModelProperty(value = "理念")
    private String idea;

    @Property(name="组织")
    @ApiModelProperty(value = "组织")
    private String organziation;




}
