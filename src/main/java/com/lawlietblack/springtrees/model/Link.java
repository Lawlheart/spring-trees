package com.lawlietblack.springtrees.model;

import javax.persistence.*;

@Entity
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Person person1;

    @ManyToOne
    private Person person2;

    private String relationship;
    private String person1Role;
    private String person2Role;

    public Link() {}

    public Link(LinkBuilder builder) {
        this.person1 = builder.person1;
        this.person2 = builder.person2;
        this.relationship = builder.relationship;
        this.person1Role = builder.person1Role;
        this.person2Role = builder.person2Role;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", person1=" + person1 +
                ", person2=" + person2 +
                ", relationship='" + relationship + '\'' +
                ", person1Role='" + person1Role + '\'' +
                ", person2Role='" + person2Role + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person getPerson1() {
        return person1;
    }

    public void setPerson1(Person person1) {
        this.person1 = person1;
    }

    public Person getPerson2() {
        return person2;
    }

    public void setPerson2(Person person2) {
        this.person2 = person2;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getPerson1Role() {
        return person1Role;
    }

    public void setPerson1Role(String person1Role) {
        this.person1Role = person1Role;
    }

    public String getPerson2Role() {
        return person2Role;
    }

    public void setPerson2Role(String person2Role) {
        this.person2Role = person2Role;
    }

    public static class LinkBuilder {
        private Person person1;
        private Person person2;
        private String relationship;
        private String person1Role;
        private String person2Role;

        public LinkBuilder(Person person) {
            this.person1 = person;
        }

        public LinkBuilder isParentOf(Person person) {
            this.person2 = person;
            this.relationship = "parent";
            this.person1Role = person1.getGender().equals("Female") ? "Mother" : "Father";
            this.person2Role = person2.getGender().equals("Female") ? "Daughter": "Son";
            return this;
        }

        public LinkBuilder isPartnerOf(Person person) {
            this.person2 = person;
            this.relationship = "partner";
            this.person1Role = "Partner";
            this.person2Role = "Partner";

            return this;
        }

        public LinkBuilder isMarriedTo(Person person) {
            this.person2 = person;
            this.relationship = "married";
            this.person1Role = person1.getGender().equals("Female") ? "Wife" : "Husband";
            this.person2Role = person2.getGender().equals("Female") ? "Wife" : "Husband";

            return this;
        }

        public LinkBuilder isSiblingOf(Person person) {
            this.person2 = person;
            this.relationship = "sibling";
            this.person1Role = person1.getGender().equals("Female") ? "Sister" : "Brother";
            this.person2Role = person2.getGender().equals("Female") ? "Sister" : "Brother";

            return this;
        }

        public Link build() {
            return new Link(this);
        }
    }
}
