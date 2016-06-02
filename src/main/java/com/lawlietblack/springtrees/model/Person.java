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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String gender;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Marriage> marriages;

    public Person() {}

    public Person(PersonBuilder builder) {
        this.name = builder.name;
        this.gender = builder.gender;
        this.marriages = builder.marriages;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", marriages=" + marriages +
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

    public List<Marriage> getMarriages() {
        return marriages;
    }

    public void setMarriages(List<Marriage> marriages) {
        this.marriages = marriages;
    }

    public void addMarriage(Marriage marriage) {
        this.marriages.add(marriage);
    }

    public static class PersonBuilder {
        private String name;
        private String gender;
        private List<Marriage> marriages = new ArrayList<>();

        public PersonBuilder(String name) {
            this.name = name;
        }

        public PersonBuilder withGender(String gender) {
            this.gender = gender;
            return this;
        }

        public PersonBuilder withMarriage(Marriage marriage) {
            this.marriages.add(marriage);
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
