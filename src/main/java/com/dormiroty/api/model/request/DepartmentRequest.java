package com.dormiroty.api.model.request;

import com.dormiroty.api.model.entity.Student;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
public class DepartmentRequest {

    private String id;

    private String departmentName;

    private Integer maxPeople;

    private Integer currentPeoples;

    private List<Student> students;
}
