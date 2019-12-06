package com.dormiroty.api.model.request;

import com.dormiroty.api.model.entity.Student;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class DeparmentRequest {
  private ObjectId objectId;
  private String name;
  private List<Student> students;
}
