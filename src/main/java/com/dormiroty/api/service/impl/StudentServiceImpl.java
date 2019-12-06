package com.dormiroty.api.service.impl;

import com.dormiroty.api.model.dto.StudentDTO;
import com.dormiroty.api.model.entity.Student;
import com.dormiroty.api.model.mapper.StudentMapper;
import com.dormiroty.api.model.request.StudentRequest;
import com.dormiroty.api.repository.StudentRepository;
import com.dormiroty.api.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentRepository studentRepository;

  @Override
  public List<StudentDTO> getAllStudent() {
    return StudentMapper.INSTANCE.toStudentDTOS(studentRepository.findAll());
  }

  @Override
  public List<StudentDTO> getAllStudentOfDepartment(String departmentName) {
    return StudentMapper.INSTANCE.toStudentDTOS(studentRepository.findAllByDepartmentName(departmentName));
  }

  @Override
  public StudentDTO createStudent(StudentRequest studentRequest) {
    Student student = new Student();
    student.setName(studentRequest.getName());
    student.setMsv(studentRequest.getMsv());
    student.setFaculity(studentRequest.getFaculity());
    student.setDepartmentName(studentRequest.getDepartmentName());
    student.setCreateDate(studentRequest.getCreatedDate());
    studentRepository.save(student);
    return StudentMapper.INSTANCE.toStudentDTO(student);
  }
}
