package com.sikaeducation.barkwire.dog;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("api/dogs")
public class DogController {
  @Autowired
  private DogRepository dogRepository;

  @GetMapping
  public List<Dog> index(){
    return (List<Dog>) dogRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Dog> show(@PathVariable(value = "id") Long id){
    Optional<Dog> dog = dogRepository.findById(id);

    if (dog.isPresent()) {
      return ResponseEntity.ok().body(dog.get());
    } else {
      return new ResponseEntity<Dog>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<Dog> create(@Validated @RequestBody Dog dog) {
    Dog createdDog = dogRepository.save(dog);
    return new ResponseEntity<Dog>(createdDog, HttpStatus.CREATED);

  }

  @PutMapping("/{id}")
  public ResponseEntity<Dog> update(@RequestBody Dog dog, @PathVariable Long id) {
    Optional<Dog> foundDog = dogRepository.findById(dog.getId());

    if (foundDog.isPresent()) {
        Dog dogToUpdate = foundDog.get();
        dogToUpdate.setName(dog.getName());
        dogToUpdate.setBreed(dog.getBreed());

        Dog updatedDog = dogRepository.save(dog);
        return new ResponseEntity<Dog>(updatedDog, HttpStatus.OK);
    } else {
        return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Dog> destroy(@PathVariable(value = "id") Long id) {
    dogRepository.deleteById(id);
    return new ResponseEntity<Dog>(HttpStatus.NO_CONTENT);
  }
}
