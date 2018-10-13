package com.song.record.tree;

import java.util.List;

/**
 * 树的节点结构
 * Created by song on 2018/10/13.
 */
public class TreeResultDto {

    private Long id;

    private String name;

    private Long parentId;

    private List<TreeResultDto> childList;

    public TreeResultDto(Long id, String name, Long parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<TreeResultDto> getChildList() {
        return childList;
    }

    public void setChildList(List<TreeResultDto> childList) {
        this.childList = childList;
    }

    @Override
    public String toString() {
        return "TreeResultDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", childList=" + childList +
                '}';
    }
}
