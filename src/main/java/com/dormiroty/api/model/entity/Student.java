package com.dormiroty.api.model.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "student")
public class Student implements Serializable {

  @Id
  private ObjectId id;

  @Field(value = "msv")
  private String msv;

  @Field(value = "name")
  private String name;

  @Field(value = "faculity")
  private String faculity;

  @Field(value = "created_date")
  private String createDate;

  @Field(value = "department_name")
  private String departmentName;
}
