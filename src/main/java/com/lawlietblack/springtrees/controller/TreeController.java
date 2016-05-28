package com.lawlietblack.springtrees.controller;

import com.lawlietblack.springtrees.model.Link;
import com.lawlietblack.springtrees.model.Person;
import com.lawlietblack.springtrees.model.Tree;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TreeController {
    private static List<Tree> allTrees = new ArrayList<>();

    public TreeController() {

//        Person homer = new Person.PersonBuilder("Homer", "Simpson").withGender("Male").build();
//        Person marge = new Person.PersonBuilder("Marge", "Simpson").withGender("Female").build();
//        Link link1 = new Link.LinkBuilder(1, 2).forTreeId(0).withRelationship("married").build();
//
//
//        Person ned = new Person.PersonBuilder("Ned", "Flanders").withGender("Male").build();
//        Person rodd = new Person.PersonBuilder("Rod", "Flanders").withGender("Male").build();
//        Link link2 = new Link.LinkBuilder(3, 4).forTreeId(1).withRelationship("parent").build();
//
//        Tree simpsons = new Tree.TreeBuilder("The Simpsons")
//                .addPerson(homer).addPerson(marge).addLink(link1).build();
//
//        Tree flanders = new Tree.TreeBuilder("The Flanders")
//                .addPerson(ned).addPerson(rodd).addLink(link2).build();
//
//        allTrees.add(simpsons);
//        allTrees.add(flanders);

    }


    @RequestMapping("/")
    public String index(ModelMap modelMap) {
        modelMap.put("trees", allTrees);
        return "home";
    }

    @RequestMapping("/trees/{id}")
    public String treeDetail(@PathVariable int id, ModelMap modelMap) {
        Tree tree = null;
        for(Tree t : allTrees) {
            if(t.getId() == id) {
                tree = t;
            }
        }
        modelMap.put("tree", tree);
        return "tree";
    };
}
