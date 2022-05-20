package com.sikaeducation.barkwire.controllers;

import java.util.List;
import java.util.Optional;

import com.sikaeducation.barkwire.models.Dog;
import com.sikaeducation.barkwire.services.DogService;

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
  private DogService dogService;

  @GetMapping
  public ResponseEntity<List<Dog>> index(){
    List<Dog> dogs = dogService.all();
    return new ResponseEntity<List<Dog>>(dogs, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Dog> show(@PathVariable Long id){
    Optional<Dog> foundDog = dogService.one(id);

    if (foundDog.isPresent()) {
      return new ResponseEntity<Dog>(foundDog.get(), HttpStatus.OK);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Dog> create(@Validated @RequestBody Dog dog) {
    Dog createdDog = dogService.create(dog);
    return new ResponseEntity<Dog>(createdDog, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Dog> update(@RequestBody Dog dog, @PathVariable Long id) {
    Optional<Dog> updatedDog = dogService.update(dog, dog.getId());

    if (updatedDog.isPresent()) {
      return new ResponseEntity<Dog>(updatedDog.get(), HttpStatus.OK);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Dog> destroy(@PathVariable Long id) {
    dogService.delete(id);
    return new ResponseEntity<Dog>(HttpStatus.NO_CONTENT);
  }
}
