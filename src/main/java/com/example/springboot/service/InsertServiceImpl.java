package com.example.springboot.service;
import com.example.springboot.model.Person;
import com.example.springboot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsertServiceImpl implements InsertService{

@Autowired
private PersonRepository personRepository;

    @Override
    public Person insert(Person person) {
        return personRepository.save(person);
    }

    @Override
    public List<Person> insertAll(List<Person> personList) {
        return personRepository.saveAll(personList);
    }
}
