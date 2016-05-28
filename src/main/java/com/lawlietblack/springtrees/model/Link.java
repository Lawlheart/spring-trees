package com.lawlietblack.springtrees.model;

import javax.persistence.*;

@Entity
public class Link {
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

}
