package com.lawlietblack.springtrees.model;

public class Tree {
    private int id;
    private int creatorId;
    private String treeName;

    public Tree(int id, int creatorId, String treeName) {
        this.id = id;
        this.creatorId = creatorId;
        this.treeName = treeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }
}
