package com.lawlietblack.springtrees.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int creatorId;
    private String name;
    private List<Person> people = new ArrayList<>();
    private List<Link> links = new ArrayList<>();

    public Tree() {};

    public Tree(int id, int creatorId, String name, List<Person> people, List<Link> links) {
        this.id = id;
        this.creatorId = creatorId;
        this.name = name;
        this.people = people;
        this.links = links;
    }

    public Tree(TreeBuilder builder) {
        this.creatorId = 1337;
        this.name = builder.name;
        this.people = builder.people;
        this.links = builder.links;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "id=" + id +
                ", creatorId=" + creatorId +
                ", name='" + name + '\'' +
                ", people=" + people +
                ", links=" + links +
                '}';
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(Link link) {
        this.links.add(link);
    }
    public void addPerson(Person person) {
        this.people.add(person);
    }

    public static class TreeBuilder {
        private String name;
        private List<Person> people = new ArrayList<>();
        private List<Link> links = new ArrayList<>();

        public TreeBuilder(String name) {
            this.name = name;
        }

        public TreeBuilder addPerson(Person person) {
            this.people.add(person);
            return this;
        }

        public TreeBuilder addLink(Link link) {
            this.links.add(link);
            return this;
        }
        public Tree build() {
            return new Tree(this);
        }
    }
}
