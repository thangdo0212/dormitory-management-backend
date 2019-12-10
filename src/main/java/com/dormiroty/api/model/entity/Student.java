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
    private String id;

    @Field(value = "studentCode")
    private String studentCode;

    @Field(value = "studentName")
    private String studentName;

    @Field(value = "faculty")
    private String faculty;

    @Field(value = "createdDate")
    private String createdDate;

    @Field(value = "departmentId")
    private String departmentId;

    @Field(value = "departmentName")
    private String departmentName;
}
