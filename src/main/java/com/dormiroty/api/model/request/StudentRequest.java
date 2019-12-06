package com.dormiroty.api.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest {
  private String name;
  private String msv;
  private String createdDate;
  private String faculity;
  private String departmentName;
}
