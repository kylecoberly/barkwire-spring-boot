package com.sikaeducation.barkwire.repositories;

import com.sikaeducation.barkwire.models.Dog;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends CrudRepository<Dog, Long> {}
