package com.example.springboot.mapper;

import com.example.springboot.api.model.PersonModel;
import com.example.springboot.model.Person;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-16T00:32:21+0700",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_281 (Oracle Corporation)"
)
@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public PersonModel personToPersonModel(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonModel personModel = new PersonModel();

        personModel.setFamilyName( person.getLastName() );
        personModel.setId( person.getId() );
        personModel.setFirstName( person.getFirstName() );

        return personModel;
    }
}
