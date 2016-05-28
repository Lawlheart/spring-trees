package com.lawlietblack.springtrees.repository;

import com.lawlietblack.springtrees.configuration.RepositoryConfiguration;
import com.lawlietblack.springtrees.model.Relationship;
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
public class RelationshipRepositoryTest {
    private RelationshipRepository relationshipRepository;

    @Autowired
    public void setRelationshipRepository(RelationshipRepository relationshipRepository) {
        this.relationshipRepository = relationshipRepository;
    }

    @Test
    public void testSaveRelationship() {
        // setup Relationship
        Relationship relationship = new Relationship();
        relationship.setName("married");

        // save Relationship
        assertNull(relationship.getId());
        relationshipRepository.save(relationship);
        assertNotNull(relationship.getId());

        // Test get Relationship (READ)
        Relationship fetch = relationshipRepository.findOne(relationship.getId());
        assertNotNull(fetch);
        assertEquals(relationship.getId(), fetch.getId());
        assertEquals(relationship.getName(), relationship.getName());

        // Test update Relationship (UPDATE)
        fetch.setName("divorced");
        relationshipRepository.save(fetch);
        Relationship updatedFetch = relationshipRepository.findOne(fetch.getId());
        assertEquals(fetch.getName(), updatedFetch.getName());

        // Test no duplication
        long relationshipCount = relationshipRepository.count();
        assertEquals(relationshipCount, 1);
        Iterable<Relationship> people = relationshipRepository.findAll();
        int count = 0;
        for(Relationship p : people) {
            count++;
        }
        assertEquals(count, 1);

        // Test delete (DELETE)
        relationshipRepository.delete(updatedFetch.getId());
        assertEquals(relationshipRepository.count(), 0);
    }
}
