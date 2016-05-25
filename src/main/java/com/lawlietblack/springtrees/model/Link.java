package com.lawlietblack.springtrees.model;

public class Link {
    private int id;
    private int treeId;
    private int person1Id;
    private int person2Id;
    private int relatinshipType;

    public Link(int id, int treeId, int person1Id, int person2Id, int relatinshipType) {
        this.id = id;
        this.treeId = treeId;
        this.person1Id = person1Id;
        this.person2Id = person2Id;
        this.relatinshipType = relatinshipType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTreeId() {
        return treeId;
    }

    public void setTreeId(int treeId) {
        this.treeId = treeId;
    }

    public int getPerson1Id() {
        return person1Id;
    }

    public void setPerson1Id(int person1Id) {
        this.person1Id = person1Id;
    }

    public int getRelatinshipType() {
        return relatinshipType;
    }

    public void setRelatinshipType(int relatinshipType) {
        this.relatinshipType = relatinshipType;
    }

    public int getPerson2Id() {
        return person2Id;
    }

    public void setPerson2Id(int person2Id) {
        this.person2Id = person2Id;
    }
}
