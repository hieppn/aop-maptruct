package com.example.springboot.service;

import com.example.springboot.api.model.GetPersons;
import com.example.springboot.api.model.PersonModel;
import com.example.springboot.mapper.PersonMapper;
import com.example.springboot.model.Person;
import com.example.springboot.repository.PersonRepository;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindServiceImpl implements FindService {
    @Autowired
    private PersonRepository repository;
    @Autowired
    private PersonMapper personMapper;
    @Override
    public ResponseEntity<GetPersons> findAll() {
        return build(repository.findAll());
    }
    @Autowired
    private ModelMapper modelMapper;
    private ResponseEntity<GetPersons> build(List<Person> personList) {
        GetPersons list = new GetPersons();
        if (list != null) {
            personList.forEach(item -> list.addPeopleItem(modelMapper.map(item, PersonModel.class)));
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @Override
    public PersonModel findById(ObjectId id) {
        Person person = repository.findById(id).orElseThrow(()-> new RuntimeException("No data for id " + id));
        return personMapper.INSTANCE.personToPersonModel( person );
    }

    @Override
    public List<Person> findByField(String field, String value) {
        return null;
    }
}
