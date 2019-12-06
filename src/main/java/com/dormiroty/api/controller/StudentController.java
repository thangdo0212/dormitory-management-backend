package com.dormiroty.api.controller;

import com.dormiroty.api.model.dto.StudentDTO;
import com.dormiroty.api.model.request.StudentRequest;
import com.dormiroty.api.model.response.RestResult;
import com.dormiroty.api.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

  @Autowired
  private StudentService studentService;

  @GetMapping("/all")
  public ResponseEntity<RestResult<List<StudentDTO>>> getAllStudent() {
    RestResult<List<StudentDTO>> restResult = new RestResult<>();
    restResult.setData(studentService.getAllStudent());
    restResult.setMessage("Get all student success");
    return new ResponseEntity<>(restResult, HttpStatus.OK);
  }

  @GetMapping("/by-department")
  public ResponseEntity<RestResult<List<StudentDTO>>> getStudentsByDepartment(
      @RequestParam("departmentName") String departmentName
  ) {
    RestResult<List<StudentDTO>> restResult = new RestResult<>();
    restResult.setData(studentService.getAllStudentOfDepartment(departmentName));
    restResult.setMessage("Get students of this department success");
    return new ResponseEntity<>(restResult, HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<RestResult<StudentDTO>> createStudent(
      @RequestBody StudentRequest studentRequest
  ) {
    RestResult<StudentDTO> restResult = new RestResult<>();
    restResult.setData(studentService.createStudent(studentRequest));
    restResult.setMessage("Create student success");
    return new ResponseEntity<>(restResult, HttpStatus.OK);
  }
}
