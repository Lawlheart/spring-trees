package com.lawlietblack.springtrees.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Marriage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer personId;


    @ManyToOne(cascade=CascadeType.ALL)
    private Person spouse;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Person> children;

    public Marriage() {}

    public Marriage(MarriageBuilder builder) {
        this.personId = builder.personId;
        this.spouse = builder.spouse;
        this.children = builder.children;
    }

    @Override
    public String toString() {
        return "Marriage{" +
                "id=" + id +
                ", person=" + personId +
                ", spouse=" + spouse +
                ", children=" + children +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void setChildren(List<Person> children) {
        this.children = children;
    }

    public static class MarriageBuilder {
        private Integer personId;
        private Person spouse;
        private List<Person> children = new ArrayList<>();

        public MarriageBuilder(Integer personId) {
            this.personId = personId;
        }

        public MarriageBuilder marriedTo(Person spouse) {
            this.spouse = spouse;
            return this;
        }

        public MarriageBuilder withChild(Person person) {
            this.children.add(person);
            return this;
        }
        public Marriage build() {
            return new Marriage(this);
        }
    }
}
