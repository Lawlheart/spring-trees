package com.lawlietblack.springtrees.repository;

import com.lawlietblack.springtrees.configuration.RepositoryConfiguration;
import com.lawlietblack.springtrees.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class PersonRepositoryTest {
    private PersonRepository personRepository;

//    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

//    @Test
    public void testSavePeople() {
        // setup Person
        Person person = new Person();
        person.setName("Homer Simpson");
        person.setGender("Male");

        // Test save Person (CREATE)
        assertNull(person.getId());
        personRepository.save(person);
        assertNotNull(person.getId());

        // Test get Person (READ)
        Person fetch = personRepository.findOne(person.getId());
        assertNotNull(fetch);
        assertEquals(person.getId(), fetch.getId());
        assertEquals(person.getName(), person.getName());

        // Test update Person (UPDATE)
        fetch.setName("Bart");
        personRepository.save(fetch);
        Person updatedFetch = personRepository.findOne(fetch.getId());
        assertEquals(fetch.getName(), updatedFetch.getName());

        // Test no duplication
        long personCount = personRepository.count();
        assertEquals(personCount, 1);
        Iterable<Person> people = personRepository.findAll();
        int count = 0;
        for(Person p : people) {
            count++;
        }
        assertEquals(count, 1);

        // Test delete (DELETE)
        personRepository.delete(updatedFetch.getId());
        assertEquals(personRepository.count(), 0);
    }
}
