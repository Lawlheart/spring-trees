package com.lawlietblack.springtrees.repository;

import com.lawlietblack.springtrees.configuration.RepositoryConfiguration;
import com.lawlietblack.springtrees.model.Tree;
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
public class TreeRepositoryTest {
    private TreeRepository treeRepository;

//    @Autowired
    public void setTreeRepository(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

//    @Test
    public void testSaveTree() {
        // setup Tree
        Tree tree = new Tree();
        tree.setName("Simpsons");
        // TODO: krb finish object setup

        // save Tree
        assertNull(tree.getId());
        treeRepository.save(tree);
        assertNotNull(tree.getId());

        // Test get Tree (READ)
        Tree fetch = treeRepository.findOne(tree.getId());
        assertNotNull(fetch);
        assertEquals(tree.getId(), fetch.getId());
        assertEquals(tree.getName(), tree.getName());

        // Test update Tree (UPDATE)
        fetch.setName("Flanders");
        treeRepository.save(fetch);
        Tree updatedFetch = treeRepository.findOne(fetch.getId());
        assertEquals(fetch.getName(), updatedFetch.getName());

        // Test no duplication
        long treeCount = treeRepository.count();
        assertEquals(treeCount, 1);
        Iterable<Tree> people = treeRepository.findAll();
        int count = 0;
        for(Tree p : people) {
            count++;
        }
        assertEquals(count, 1);

        // Test delete (DELETE)
        treeRepository.delete(updatedFetch.getId());
        assertEquals(treeRepository.count(), 0);
    }
}
