package com.dormiroty.api.model.dto;

import com.dormiroty.api.model.entity.Student;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class DepartmentDTO {

    private String id;

    private String departmentName;

    private Integer maxPeoples;

    private Integer currentPeoples;

    private List<Student> students;
}
