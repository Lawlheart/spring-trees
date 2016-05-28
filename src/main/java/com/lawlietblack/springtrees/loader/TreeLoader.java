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
        Link link1 = new LinkBuilder(homer);

        personRepository.save(Arrays.asList(homer, marge, bart, lisa, maggie));
        treeRepository.save(simpsons);

        Tree flanders = new Tree();
        flanders.setName("The Flanders");
        flanders.setCreatorId(1337);
        Person ned = new Person.PersonBuilder("Ned", "Flanders").withGender("Male").build();
        Person rodd = new Person.PersonBuilder("Rodd", "Flanders").withGender("Male").build();
        Person todd = new Person.PersonBuilder("Todd", "Flanders").withGender("Male").build();
        personRepository.save(Arrays.asList(ned, rodd, todd));
        flanders.setPeople(Arrays.asList(ned, rodd, todd));
        treeRepository.save(flanders);
    }
}
