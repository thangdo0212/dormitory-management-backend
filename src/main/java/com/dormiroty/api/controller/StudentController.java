package com.dormiroty.api.controller;

import com.dormiroty.api.model.dto.StudentDTO;
import com.dormiroty.api.model.request.StudentRequest;
import com.dormiroty.api.model.response.RestResult;
import com.dormiroty.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping()
    public ResponseEntity<RestResult<List<StudentDTO>>> getAllStudents() {
        RestResult<List<StudentDTO>> restResult = new RestResult<>();
        restResult.setData(studentService.getAllStudent());
        restResult.setMessage("Get all students success");
        return new ResponseEntity<>(restResult, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<RestResult<StudentDTO>> createStudent(@RequestBody StudentRequest request) {
        RestResult<StudentDTO> restResult = new RestResult<>();
        restResult.setData(studentService.createStudent(request));
        restResult.setMessage("Create student success");
        return new ResponseEntity<>(restResult, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<RestResult<StudentDTO>> updateStudent(@RequestBody StudentRequest request) {
        RestResult<StudentDTO> restResult = new RestResult<>();
        restResult.setData(studentService.updateStudent(request));
        restResult.setMessage("Update student success");
        return new ResponseEntity<>(restResult, HttpStatus.OK);
    }

    @DeleteMapping()
    public void deleteStudent(@RequestParam("studentCode") String studentCode) {
        studentService.deleteStudent(studentCode);
    }

    @GetMapping("/search")
    public ResponseEntity<RestResult<StudentDTO>> searchStudentByCode(@RequestParam("studentCode") String studentCode) {
        RestResult<StudentDTO> restResult = new RestResult<>();
        restResult.setData(studentService.searchStudentByStudentCode(studentCode));
        restResult.setMessage("Create student success");
        return new ResponseEntity<>(restResult, HttpStatus.OK);
    }
}
