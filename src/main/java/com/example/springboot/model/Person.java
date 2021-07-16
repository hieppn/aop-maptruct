package com.example.springboot.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "persons")
public class Person {
    @Id
    private ObjectId id;
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;
}