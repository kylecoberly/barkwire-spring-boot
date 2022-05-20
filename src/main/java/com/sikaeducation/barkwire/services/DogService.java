package com.sikaeducation.barkwire.services;

import java.util.List;
import java.util.Optional;

import com.sikaeducation.barkwire.models.Dog;
import com.sikaeducation.barkwire.repositories.DogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DogService {
  @Autowired
  private DogRepository dogRepository;

  public List<Dog> all(){
    return (List<Dog>) dogRepository.findAll();
  }

  public Optional<Dog> one(Long id){
    return dogRepository.findById(id);
  }

  public Dog create(Dog dog) {
    return dogRepository.save(dog);
  }

  public Optional<Dog> update(Dog dog, Long id) {
    Optional<Dog> foundDog = dogRepository.findById(dog.getId());

    if (foundDog.isPresent()) {
        Dog dogToUpdate = foundDog.get();
        dogToUpdate.setName(dog.getName());
        dogToUpdate.setBreed(dog.getBreed());

        Dog updatedDog = dogRepository.save(dog);
        return Optional.of(updatedDog);
      } else {
        return Optional.empty();
      }
  }

  public void delete(Long id) {
    dogRepository.deleteById(id);
  }
}
