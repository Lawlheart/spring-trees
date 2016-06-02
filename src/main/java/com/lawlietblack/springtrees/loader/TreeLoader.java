package com.lawlietblack.springtrees.loader;

import com.lawlietblack.springtrees.model.Marriage;
import com.lawlietblack.springtrees.model.Marriage.MarriageBuilder;
import com.lawlietblack.springtrees.model.Person;
import com.lawlietblack.springtrees.model.Person.PersonBuilder;
import com.lawlietblack.springtrees.model.Tree;
import com.lawlietblack.springtrees.repository.MarriageRepository;
import com.lawlietblack.springtrees.repository.PersonRepository;
import com.lawlietblack.springtrees.repository.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TreeLoader implements ApplicationListener<ContextRefreshedEvent> {
    private TreeRepository treeRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MarriageRepository marriageRepository;

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
        Person marge = new PersonBuilder("Marge Simpson").withGender("Female").build();
        Person homer = new PersonBuilder("Homer Simpson").withGender("Male").build();
        Person lisa = new PersonBuilder("Lisa Simpson").withGender("Female").build();
        Person maggie = new PersonBuilder("Maggie Simpson").withGender("Female").build();
        Person bart = new PersonBuilder("Bart Simpson").withGender("Male").build();

        Marriage margeToHomer = new MarriageBuilder(marge.getId()).marriedTo(homer)
                .withChild(bart).withChild(lisa).withChild(maggie).build();
        marge.addMarriage(margeToHomer);


        // Create and Save
        simpsons.setFirst(marge);
        simpsons.setPeople(Arrays.asList(homer, marge, bart, lisa, maggie));
//        personRepository.save(Arrays.asList(homer, marge, bart, lisa, maggie));
//        marriageRepository.save(margeToHomer);
        treeRepository.save(simpsons);

        // Create Tree;
        Tree flanders = new Tree();
        flanders.setName("The Flanders");
        flanders.setCreatorId(1337);

//        // Add People
//        Person ned = new Person.PersonBuilder("Ned Flanders").withGender("Male").build();
//        Person rodd = new Person.PersonBuilder("Rodd Flanders").withGender("Male").build();
//        Person todd = new Person.PersonBuilder("Todd Flanders").withGender("Male").build();
//        Person maude = new Person.PersonBuilder("Maude Flanders").withGender("Female").build();
//
//        ned.addMarriage(maude);
//        ned.addChild(rodd);
//        ned.addChild(todd);
//
//        maude.addMarriage(ned);
//        maude.addChild(rodd);
//        maude.addChild(todd);
//
//        // Create and Save
//        flanders.setPeople(Arrays.asList(ned, rodd, todd, maude));
//        personRepository.save(Arrays.asList(ned, rodd, todd, maude));
//        treeRepository.save(flanders);
    }
}
