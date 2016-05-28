package com.lawlietblack.springtrees.repository;

import com.lawlietblack.springtrees.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}
