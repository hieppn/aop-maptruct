package com.example.springboot.api.model;

import java.util.Objects;
import com.example.springboot.api.model.PersonModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetPersons
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-16T00:31:56.149+07:00[Asia/Bangkok]")

public class GetPersons   {
  @JsonProperty("people")
  @Valid
  private List<PersonModel> people = null;

  public GetPersons people(List<PersonModel> people) {
    this.people = people;
    return this;
  }

  public GetPersons addPeopleItem(PersonModel peopleItem) {
    if (this.people == null) {
      this.people = new ArrayList<>();
    }
    this.people.add(peopleItem);
    return this;
  }

  /**
   * Get people
   * @return people
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<PersonModel> getPeople() {
    return people;
  }

  public void setPeople(List<PersonModel> people) {
    this.people = people;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetPersons getPersons = (GetPersons) o;
    return Objects.equals(this.people, getPersons.people);
  }

  @Override
  public int hashCode() {
    return Objects.hash(people);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetPersons {\n");
    
    sb.append("    people: ").append(toIndentedString(people)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

