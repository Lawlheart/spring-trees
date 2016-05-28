package com.lawlietblack.springtrees.loader;

import com.lawlietblack.springtrees.model.Role;
import com.lawlietblack.springtrees.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RoleLoader implements ApplicationListener<ContextRefreshedEvent> {
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<Role> roles = Arrays.asList(
                new Role("Partner"),
                new Role("Wife"),
                new Role("Husband"),
                new Role("Mother"),
                new Role("Father"),
                new Role("Sister"),
                new Role("Brother"),
                new Role("Daughter"),
                new Role("Son")
        );
        roleRepository.save(roles);

    }
}
