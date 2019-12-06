package com.dormiroty.api.model.dto;

import com.dormiroty.api.model.entity.Student;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class DepartmentDTO {

  private ObjectId id;
  private String name;
  private Integer totalBed;
  private Integer nullBed;
  private List<Student> students;
}
