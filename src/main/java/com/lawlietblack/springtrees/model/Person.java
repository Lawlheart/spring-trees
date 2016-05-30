package com.lawlietblack.springtrees.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String gender;

    @ManyToMany
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Person> marriages;

    @ManyToMany
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Person> children;

    public Person() {}

    public Person(PersonBuilder builder) {
        this.name = builder.name;
        this.gender = builder.gender;
        this.marriages = builder.marriages;
        this.children = builder.children;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String firstName) {
        this.name = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Person> getMarriages() {
        return marriages;
    }

    public void setMarriages(List<Person> marriages) {
        this.marriages = marriages;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void setChildren(List<Person> children) {
        this.children = children;
    }

    public void addMarriage(Person marriage) {
        this.marriages.add(marriage);
    }
    public void addChild(Person child) {
        this.children.add(child);
    }

    public static class PersonBuilder {
        private String name;
        private String gender;
        private List<Person> marriages = new ArrayList<>();
        private List<Person> children = new ArrayList<>();

        public PersonBuilder(String name) {
            this.name = name;
        }

        public PersonBuilder withGender(String gender) {
            this.gender = gender;
            return this;
        }

        public PersonBuilder marriedTo(Person person) {
            this.marriages.add(person);
            return this;
        }

        public PersonBuilder withChild(Person person) {
            this.children.add(person);
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
