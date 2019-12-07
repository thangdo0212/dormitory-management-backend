package com.dormiroty.api.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class StudentDTO {

    private ObjectId id;

    private String studentCode;

    private String studentName;

    private String faculty;

    private String createdDate;

    private ObjectId departmentId;

    private String departmentName;
}
