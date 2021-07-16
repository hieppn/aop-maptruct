package com.example.springboot.controller;

import com.example.springboot.api.PersonApi;
import com.example.springboot.api.model.CreatePersonRequest;
import com.example.springboot.api.model.GetPersons;
import com.example.springboot.api.model.ObjectCreationSuccessResponse;
import com.example.springboot.api.model.PersonModel;
import com.example.springboot.model.Person;
import com.example.springboot.service.FindService;
import com.example.springboot.service.InsertService;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Log4j2
@Controller
@RestController
public class PersonController implements PersonApi{
    @Autowired
    private InsertService insertService;
    @Autowired
    private FindService findService;
    @Autowired
    private ModelMapper modelMapper;
    public PersonController(InsertService insertService, FindService findService) {
        this.insertService = insertService;
        this.findService = findService;
    }

    @Override
    public ResponseEntity<PersonModel> findPersonById(Object id) {
        return new ResponseEntity<>(findService.findById(new ObjectId(id.toString())), HttpStatus.OK);    }

    @Override
    public ResponseEntity<GetPersons> getPersons() {
        return findService.findAll();
    }

    @Override
    public ResponseEntity<ObjectCreationSuccessResponse> savePerson(@Valid CreatePersonRequest createPersonRequest) {
        Person info  = insertService.insert(modelMapper.map(createPersonRequest, Person.class));
        ObjectCreationSuccessResponse result = new ObjectCreationSuccessResponse();
        result.setId(info.getId().toString());
        result.setResponseCode(HttpStatus.CREATED.value());
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
