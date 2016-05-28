package com.lawlietblack.springtrees.repository;

import com.lawlietblack.springtrees.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    List<Person> findByLastName(String lastName);
}
