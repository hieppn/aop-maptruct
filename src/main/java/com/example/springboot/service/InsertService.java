package com.example.springboot.service;


import com.example.springboot.model.Person;

import java.util.List;

public interface InsertService{
    Person insert(Person person);
    List<Person> insertAll(List<Person> personList);
}