package com.common.project.entity;

public class TestEntity {
    private int id;
    private String name;
    private int parentId;
    private int level;

    public TestEntity(int id, String name, int parentId,int level) {
        super();
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Location [id=" + id + ", name=" + name + ", parentId=" + parentId + ", level=" + level + "]";
    }
}
