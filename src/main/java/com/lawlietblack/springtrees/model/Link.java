package com.lawlietblack.springtrees.model;

import com.lawlietblack.springtrees.repository.RelationshipRepository;
import com.lawlietblack.springtrees.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
public class Link {
    @Autowired
    private static RelationshipRepository relationshipRepository;
    @Autowired
    private static RoleRepository roleRepository;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private Tree tree;

    @ManyToOne
    private Person person1;

    @ManyToOne
    private Person person2;

    @ManyToOne
    private Relationship relationship;

    @ManyToOne
    private Role person1Role;

    @ManyToOne
    private Role person2Role;

    public Link() {}

    public Link(LinkBuilder linkBuilder) {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
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

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public Role getPerson1Role() {
        return person1Role;
    }

    public void setPerson1Role(Role person1Role) {
        this.person1Role = person1Role;
    }

    public Role getPerson2Role() {
        return person2Role;
    }

    public void setPerson2Role(Role person2Role) {
        this.person2Role = person2Role;
    }

    public static class LinkBuilder {
        private Tree tree;
        private Person person1;
        private Person person2;
        private Relationship relationship;
        private Role person1Role;
        private Role person2Role;

        public LinkBuilder(Tree tree, Person person) {
            this.tree = tree;
            this.person1 = person;
        }

        public LinkBuilder isParentOf(Person person) {
            this.person2 = person;
            this.relationship = relationshipRepository.findOne(2);
            this.person1Role = roleRepository.findOne()
        }
    }
}
