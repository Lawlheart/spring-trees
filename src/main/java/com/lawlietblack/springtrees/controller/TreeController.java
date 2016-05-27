package com.lawlietblack.springtrees.controller;

import com.lawlietblack.springtrees.model.Link;
import com.lawlietblack.springtrees.model.Person;
import com.lawlietblack.springtrees.model.Tree;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TreeController {
    private static List<Tree> allTrees = new ArrayList<>();

    public TreeController() {
        Tree simpsons = new Tree.TreeBuilder("The Simpsons")
                .addPerson(new Person.PersonBuilder("Homer", "Simpson").withGender("Male")
                        .withDateOfBirth(LocalDate.of(1955, 5, 13)).build())
                .addPerson(new Person.PersonBuilder("Marge", "Simpson").withGender("Female")
                        .withDateOfBirth(LocalDate.of(1955, 3, 19)).build())
                .addLink(new Link.LinkBuilder(1, 2).forTreeId(0).withRelationship("married").build()
                ).build();
        Tree flanders = new Tree.TreeBuilder("The Flanders")
                .addPerson(new Person.PersonBuilder("Ned", "Flanders").withGender("Male")
                        .withDateOfBirth(LocalDate.of(1935, 12, 13)).build())
                .addPerson(new Person.PersonBuilder("Rod", "Flanders").withGender("Male")
                        .withDateOfBirth(LocalDate.of(1975, 5, 12)).build())
                .addLink(new Link.LinkBuilder(3, 4).forTreeId(1).withRelationship("parent").build()
                ).build();


        allTrees.add(simpsons);
        allTrees.add(flanders);
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
