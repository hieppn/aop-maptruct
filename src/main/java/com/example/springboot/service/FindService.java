package com.example.springboot.service;

import com.example.springboot.api.model.GetPersons;
import com.example.springboot.api.model.PersonModel;
import com.example.springboot.model.Person;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FindService{
    ResponseEntity<GetPersons> findAll();
    PersonModel findById(ObjectId id);
    List<Person> findByField(String field, String value);
}
