package com.cusc.cuscai.dto;

import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;

import java.util.List;

/**
 * 用于输出图
 */
public class GraphDTO {
    private List<Object> nodes;

    private List<Object> links;

    public List<Object> getNodes() {
        return nodes;
    }

    public void setNodes(List<Object> nodes) {
        this.nodes = nodes;
    }

    public List<Object> getLinks() {
        return links;
    }

    public void setLinks(List<Object> links) {
        this.links = links;
    }
}
