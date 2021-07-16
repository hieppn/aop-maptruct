package com.example.springboot.repository;


import com.example.springboot.model.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface PersonRepository extends MongoRepository<Person, ObjectId>, QueryByExampleExecutor<Person> {
}