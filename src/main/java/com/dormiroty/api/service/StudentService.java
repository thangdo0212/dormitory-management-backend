package com.dormiroty.api.service;

import com.dormiroty.api.model.dto.StudentDTO;
import com.dormiroty.api.model.request.StudentRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    StudentDTO createStudent(StudentRequest studentRequest);

    StudentDTO searchStudentByStudentCode(String studentCode);

    void deleteStudent(String studentCode);

    StudentDTO updateStudent(StudentRequest studentRequest);

    List<StudentDTO> getAllStudent();
}
