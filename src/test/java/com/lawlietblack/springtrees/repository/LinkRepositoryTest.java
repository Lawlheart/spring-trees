package com.lawlietblack.springtrees.repository;

import com.lawlietblack.springtrees.configuration.RepositoryConfiguration;
import com.lawlietblack.springtrees.model.Link;
import com.lawlietblack.springtrees.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class LinkRepositoryTest {
    private LinkRepository linkRepository;
    private PersonRepository personRepository;

    @Autowired
    public void setLinkRepository(LinkRepository linkRepository, PersonRepository personRepository) {
        this.linkRepository = linkRepository;
        this.personRepository = personRepository;
    }

    @Test
    public void testSaveLink() {
        // setup Link
        Link link = new Link();
        Person homer = new Person();
        Person marge = new Person();
        homer.setFirstName("Homer");
        marge.setFirstName("Marge");
        personRepository.save(homer);
        personRepository.save(marge);
        link.setPerson1(homer);

        // save Link
        assertNull(link.getId());
        linkRepository.save(link);
        assertNotNull(link.getId());

        // Test get Link (READ)
        Link fetch = linkRepository.findOne(link.getId());
        assertNotNull(fetch);
        assertEquals(link.getId(), fetch.getId());
        assertEquals(link.getPerson1(), link.getPerson1());

        // Test update Link (UPDATE)
        fetch.setPerson1(marge);
        linkRepository.save(fetch);
        Link updatedFetch = linkRepository.findOne(fetch.getId());
        assertEquals(fetch.getPerson1().getFirstName(), updatedFetch.getPerson1().getFirstName());

        // Test no duplication
        long linkCount = linkRepository.count();
        assertEquals(linkCount, 1);
        Iterable<Link> people = linkRepository.findAll();
        int count = 0;
        for(Link p : people) {
            count++;
        }
        assertEquals(count, 1);

        // Test delete (DELETE)
        linkRepository.delete(updatedFetch.getId());
        assertEquals(linkRepository.count(), 0);

        // Clear out repository for other tests
        personRepository.deleteAll();
    }
}
