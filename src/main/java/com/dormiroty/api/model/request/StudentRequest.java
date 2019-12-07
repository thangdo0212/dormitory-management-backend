package com.dormiroty.api.model.request;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class StudentRequest {

    private ObjectId id;

    private String studentCode;

    private String studentName;

    private String faculty;

    private String createDate;

    private ObjectId departmentId;

    private String departmentName;
}
