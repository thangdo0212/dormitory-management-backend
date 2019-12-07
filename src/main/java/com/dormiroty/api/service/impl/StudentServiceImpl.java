package com.dormiroty.api.service.impl;

import com.dormiroty.api.model.dto.StudentDTO;
import com.dormiroty.api.model.entity.Department;
import com.dormiroty.api.model.entity.Student;
import com.dormiroty.api.model.mapper.StudentMapper;
import com.dormiroty.api.model.request.StudentRequest;
import com.dormiroty.api.repository.DepartmentRepository;
import com.dormiroty.api.repository.StudentRepository;
import com.dormiroty.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public StudentDTO createStudent(StudentRequest studentRequest) {
        Student student = new Student();
        student.setStudentCode(studentRequest.getStudentCode());
        student.setStudentName(studentRequest.getStudentName());
        student.setFaculty(studentRequest.getFaculty());
        student.setCreatedDate(LocalDate.now().toString());
        student.setDepartmentId(studentRequest.getDepartmentId());
        student.setDepartmentName(studentRequest.getDepartmentName());
        studentRepository.save(student);

        // add student to department
        Department department = departmentRepository.findById(studentRequest.getDepartmentId()).get();
        List<Student> currentStudents = department.getStudents();
        if(department.getCurrentPeoples() < 8) {
            currentStudents.add(student);
            department.setStudents(currentStudents);
            department.setCurrentPeoples(currentStudents.size());
            departmentRepository.save(department);
        } else {
            throw new IllegalArgumentException("Department is full");
        }
        return StudentMapper.INSTANCE.toStudentDTO(student);
    }

    @Override
    public StudentDTO searchStudentByStudentCode(String studentCode) {
        return StudentMapper.INSTANCE.toStudentDTO(studentRepository.findByStudentCode(studentCode));
    }

    @Override
    public void deleteStudent(String studentCode) {
        studentRepository.deleteByStudentCode(studentCode);
    }

    /** move department */
    @Override
    public StudentDTO updateStudent(StudentRequest studentRequest) {
        Student student = studentRepository.findByStudentCode(studentRequest.getStudentCode());
        // remove student at old department
        Department department = departmentRepository.findById(student.getDepartmentId()).get();
        List<Student> studentOfDepartment = department.getStudents();
        for (Student s : studentOfDepartment) {
            if (s.getDepartmentId().equals(student.getDepartmentId())) {
                studentOfDepartment.remove(s);
            }
            department.setStudents(studentOfDepartment);
            department.setCurrentPeoples(studentOfDepartment.size());
            departmentRepository.save(department);
            break;
        }
        student.setDepartmentName(studentRequest.getDepartmentName());
        student.setDepartmentId(studentRequest.getDepartmentId());
        studentRepository.save(student);
        // update information of new department of student
        Department newDepartment = departmentRepository.findById(studentRequest.getDepartmentId()).get();
        List<Student> currentStudents = newDepartment.getStudents();
        if(department.getCurrentPeoples() < 8) {
            currentStudents.add(student);
            department.setStudents(currentStudents);
            department.setCurrentPeoples(currentStudents.size());
            departmentRepository.save(department);
        } else {
            throw new IllegalArgumentException("Department is full");
        }
        return null;
    }

    @Override
    public List<StudentDTO> getAllStudent() {
        return StudentMapper.INSTANCE.toStudentDTOS(studentRepository.findAll());
    }
}
