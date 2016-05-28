package com.lawlietblack.springtrees.loader;

import com.lawlietblack.springtrees.model.Link;
import com.lawlietblack.springtrees.model.Person;
import com.lawlietblack.springtrees.model.Tree;
import com.lawlietblack.springtrees.repository.LinkRepository;
import com.lawlietblack.springtrees.repository.PersonRepository;
import com.lawlietblack.springtrees.repository.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class TreeLoader implements ApplicationListener<ContextRefreshedEvent> {
    private TreeRepository treeRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    public void setTreeRepository(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Create Tree
        Tree simpsons = new Tree();
        simpsons.setName("The Simpsons");
        simpsons.setCreatorId(1337);

        // Add People
        Person homer = new Person.PersonBuilder("Homer", "Simpson").withGender("Male").build();
        Person marge = new Person.PersonBuilder("Marge", "Simpson").withGender("Female").build();
        Person bart = new Person.PersonBuilder("Bart", "Simpson").withGender("Male").build();
        Person lisa = new Person.PersonBuilder("Lisa", "Simpson").withGender("Female").build();
        Person maggie = new Person.PersonBuilder("Maggie", "Simpson").withGender("Female").build();
        simpsons.setPeople(Arrays.asList(homer, marge, bart, lisa, maggie));

        // Add Links
        Link link1 = new Link.LinkBuilder(marge).isMarriedTo(homer).build();
        Link link2 = new Link.LinkBuilder(marge).isParentOf(bart).build();
        Link link3 = new Link.LinkBuilder(marge).isParentOf(lisa).build();
        Link link4 = new Link.LinkBuilder(marge).isParentOf(maggie).build();
        Link link5 = new Link.LinkBuilder(homer).isParentOf(bart).build();
        Link link6 = new Link.LinkBuilder(homer).isParentOf(lisa).build();
        Link link7 = new Link.LinkBuilder(homer).isParentOf(maggie).build();
        simpsons.setLinks(Arrays.asList(link1, link2, link3, link4, link5, link6, link7));

        // Save to DB
        personRepository.save(Arrays.asList(homer, marge, bart, lisa, maggie));
        linkRepository.save(Arrays.asList(link1, link2, link3, link4, link5, link6, link7));
        treeRepository.save(simpsons);

        // Create Tree;
        Tree flanders = new Tree();
        flanders.setName("The Flanders");
        flanders.setCreatorId(1337);

        // Add People
        Person ned = new Person.PersonBuilder("Ned", "Flanders").withGender("Male").build();
        Person rodd = new Person.PersonBuilder("Rodd", "Flanders").withGender("Male").build();
        Person todd = new Person.PersonBuilder("Todd", "Flanders").withGender("Male").build();
        flanders.setPeople(Arrays.asList(ned, rodd, todd));

        // Add Links
        Link link8 = new Link.LinkBuilder(ned).isParentOf(rodd).build();
        Link link9 = new Link.LinkBuilder(ned).isParentOf(todd).build();
        flanders.setLinks(Arrays.asList(link8, link9));

        // Save to DB
        personRepository.save(Arrays.asList(ned, rodd, todd));
        linkRepository.save(Arrays.asList(link8, link9));
        treeRepository.save(flanders);
    }
}
