package com.dormiroty.api.service;

import com.dormiroty.api.model.dto.StudentDTO;
import com.dormiroty.api.model.request.StudentRequest;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
  List<StudentDTO> getAllStudent();
  List<StudentDTO> getAllStudentOfDepartment(String departmentName);
  StudentDTO createStudent(StudentRequest studentRequest);
}
