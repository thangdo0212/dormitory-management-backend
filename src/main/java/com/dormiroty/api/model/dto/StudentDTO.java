package com.dormiroty.api.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class StudentDTO {

    private String id;

    private String studentCode;

    private String studentName;

    private String faculty;

    private String createdDate;

    private String departmentId;

    private String departmentName;
}
