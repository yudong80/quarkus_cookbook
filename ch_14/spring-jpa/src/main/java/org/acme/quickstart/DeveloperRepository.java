package org.acme.quickstart;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DeveloperRepository extends CrudRepository<Developer, Long> { // <1>
    List<Developer> findByName(String name); // <2>
}