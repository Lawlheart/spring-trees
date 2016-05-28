package com.lawlietblack.springtrees.repository;

import com.lawlietblack.springtrees.configuration.RepositoryConfiguration;
import com.lawlietblack.springtrees.model.Role;
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
public class RoleRepositoryTest {
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Test
    public void testSaveRole() {
        // setup Role
        Role role = new Role();
        role.setName("Father");

        // save Role
        assertNull(role.getId());
        roleRepository.save(role);
        assertNotNull(role.getId());

        // Test get Role (READ)
        Role fetch = roleRepository.findOne(role.getId());
        assertNotNull(fetch);
        assertEquals(role.getId(), fetch.getId());
        assertEquals(role.getName(), role.getName());

        // Test update Role (UPDATE)
        fetch.setName("Son");
        roleRepository.save(fetch);
        Role updatedFetch = roleRepository.findOne(fetch.getId());
        assertEquals(fetch.getName(), updatedFetch.getName());

        // Test no duplication
        long roleCount = roleRepository.count();
        assertEquals(roleCount, 1);
        Iterable<Role> people = roleRepository.findAll();
        int count = 0;
        for(Role p : people) {
            count++;
        }
        assertEquals(count, 1);

        // Test delete (DELETE)
        roleRepository.delete(updatedFetch.getId());
        assertEquals(roleRepository.count(), 0);
    }
}
