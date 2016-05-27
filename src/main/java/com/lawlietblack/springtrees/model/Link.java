package com.lawlietblack.springtrees.model;

public class Link {
    private int id;
    private int treeId;
    private int person1Id;
    private int person2Id;
    private int relationshipType;

    public Link(int id, int treeId, int person1Id, int person2Id, int relationshipType) {
        this.id = id;
        this.treeId = treeId;
        this.person1Id = person1Id;
        this.person2Id = person2Id;
        this.relationshipType = relationshipType;
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
        return relationshipType;
    }

    public void setRelatinshipType(int relatinshipType) {
        this.relationshipType = relatinshipType;
    }

    public int getPerson2Id() {
        return person2Id;
    }

    public void setPerson2Id(int person2Id) {
        this.person2Id = person2Id;
    }
}
