package com.example.springboot.mapper;

import com.example.springboot.api.model.PersonModel;
import com.example.springboot.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper( PersonMapper.class );
 @Mapping(source = "lastName", target = "familyName")
    PersonModel personToPersonModel(Person person);
}
