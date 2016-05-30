package com.lawlietblack.springtrees.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer creatorId;
    private String name;

    @OneToMany
    private List<Person> people = new ArrayList<>();

    public Tree() {}

    public Tree(Integer id, Integer creatorId, String name, List<Person> people) {
        this.id = id;
        this.creatorId = creatorId;
        this.name = name;
        this.people = people;
    }

    public Tree(TreeBuilder builder) {
        this.creatorId = 1337;
        this.name = builder.name;
        this.people = builder.people;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "id=" + id +
                ", creatorId=" + creatorId +
                ", name='" + name + '\'' +
                ", people=" + people +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
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

    public void addPerson(Person person) {
        this.people.add(person);
    }

    public static class TreeBuilder {
        private String name;
        private List<Person> people = new ArrayList<>();

        public TreeBuilder(String name) {
            this.name = name;
        }

        public TreeBuilder addPerson(Person person) {
            this.people.add(person);
            return this;
        }

        public Tree build() {
            return new Tree(this);
        }
    }
}
