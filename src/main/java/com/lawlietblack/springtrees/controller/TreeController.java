package com.lawlietblack.springtrees.controller;

import com.lawlietblack.springtrees.model.Marriage;
import com.lawlietblack.springtrees.model.Person;
import com.lawlietblack.springtrees.model.Tree;
import com.lawlietblack.springtrees.repository.PersonRepository;
import com.lawlietblack.springtrees.repository.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
public class TreeController {
    private static List<Tree> allTrees = new ArrayList<>();

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TreeRepository treeRepository;

    @RequestMapping("/")
    public String index(ModelMap modelMap) {
        allTrees = (List<Tree>) treeRepository.findAll();
        modelMap.put("trees", allTrees);
        return "home";
    }

    @RequestMapping("/display/{id}")
    public String treeDetail(@PathVariable int id, ModelMap modelMap) {
        Tree tree = treeRepository.findOne(id);
        modelMap.put("tree", tree);
        return "tree";
    }

    @RequestMapping(value = "/data/trees/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody Tree treeJson(@PathVariable int id, ModelMap modelMap) {
        return treeRepository.findOne(id);
    }
}
