package com.dormiroty.api.model.entity;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "department")
public class Department implements Serializable {

  @Id
  private ObjectId id;

  @Field(value = "name")
  private String name;

  @Field(value = "total_bed")
  private Integer totalBed;

  @Field(value = "null_bed")
  private Integer nullBed;

  @Field(value = "students")
  private List<Student> students;
}
