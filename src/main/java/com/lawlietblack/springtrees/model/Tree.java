package com.lawlietblack.springtrees.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trees")
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer creatorId;
    private String name;

    @OneToOne(cascade=CascadeType.ALL)
    private Person first;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Person> people = new ArrayList<>();

    public Tree() {}

    public Tree(Integer id, Integer creatorId, String name, Person first, List<Person> people) {
        this.id = id;
        this.creatorId = creatorId;
        this.name = name;
        this.first = first;
        this.people = people;
    }

    public Tree(TreeBuilder builder) {
        this.creatorId = 1337;
        this.name = builder.name;
        this.people = builder.people;
        this.first = builder.first;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "id=" + id +
                ", creatorId=" + creatorId +
                ", name='" + name + '\'' +
                ", first=" + first +
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

    public Person getFirst() {
        return first;
    }

    public void setFirst(Person first) {
        this.first = first;
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
        private String creatorId;
        private List<Person> people = new ArrayList<>();
        public Person first;

        public TreeBuilder(String name, Person first) {
            this.name = name;
            this.first = first;
        }
        public TreeBuilder addCreatorId(String creatorId) {
            this.creatorId = creatorId;
            return this;
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
