package com.lawlietblack.springtrees.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Link(LinkBuilder builder) {
        this.treeId = builder.treeId;
        this.person1Id = builder.person1Id;
        this.person2Id = builder.person2Id;
        this.relationshipType = builder.relationshipType;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", treeId=" + treeId +
                ", person1Id=" + person1Id +
                ", person2Id=" + person2Id +
                ", relationshipType=" + relationshipType +
                '}';
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

    public static class LinkBuilder {
        private int treeId;
        private int person1Id;
        private int person2Id;
        private int relationshipType;

        public LinkBuilder(int person1Id, int person2Id) {
            this.person1Id = person1Id;
            this.person2Id = person2Id;
        }

        public LinkBuilder forTreeId(int treeId) {
            this.treeId = treeId;
            return this;
        }

        public LinkBuilder withRelationship(String relationship) {
            switch (relationship) {
                case "married":
                    this.relationshipType = 1;
                    break;
                case "parent":
                    this.relationshipType = 2;
                    break;
                default:
                    this.relationshipType = 99;
            }
            return this;
        }

        public Link build() {
            return new Link(this);
        }
    }
}
