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
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public StudentDTO createStudent(StudentRequest studentRequest) {
        Department department = departmentRepository.findById(studentRequest.getDepartmentId()).get();

        if(department == null) {
            throw new IllegalArgumentException("Department not found");
        }

        if(department.getCurrentPeoples() == 8) {
            throw new IllegalArgumentException("Department is full");
        }
        Student student = new Student();
        student.setStudentCode(studentRequest.getStudentCode());
        student.setStudentName(studentRequest.getStudentName());
        student.setFaculty(studentRequest.getFaculty());
        student.setCreatedDate(LocalDate.now().toString());
        student.setDepartmentId(studentRequest.getDepartmentId());
        student.setDepartmentName(studentRequest.getDepartmentName());
        studentRepository.save(student);

        // add student to department
        List<Student> currentStudents = department.getStudents();
        if (department.getCurrentPeoples() < 8) {
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
        Student student = studentRepository.findByStudentCode(studentCode);
        if(student == null) {
            throw new IllegalArgumentException("student not exist");
        }
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
        studentRepository.deleteByStudentCode(studentCode);
    }


    /**
     * move department
     */
    @Override
    public StudentDTO updateStudent(StudentRequest studentRequest) {
        Student student = studentRepository.findByStudentCode(studentRequest.getStudentCode());
        if(student == null) {
            throw new IllegalArgumentException("student not exist");
        }
        // remove student at old department
        Department department = departmentRepository.findById(student.getDepartmentId()).get();
        List<Student> studentOfDepartment = department.getStudents();
        for (Student s : studentOfDepartment) {
            if (s.getStudentCode().equals(student.getStudentCode())) {
                studentOfDepartment.remove(s);
                department.setStudents(studentOfDepartment);
                department.setCurrentPeoples(studentOfDepartment.size());
                departmentRepository.save(department);
                break;
            }
        }
        student.setDepartmentName(studentRequest.getDepartmentName());
        student.setDepartmentId(studentRequest.getDepartmentId());
        Department newDepartment = departmentRepository.findById(studentRequest.getDepartmentId()).get();
        List<Student> currentStudents = newDepartment.getStudents();

        if (newDepartment.getCurrentPeoples() < 8) {
            currentStudents.add(student);
            newDepartment.setStudents(currentStudents);
            newDepartment.setCurrentPeoples(currentStudents.size());
            departmentRepository.save(newDepartment);
        } else {
            throw new IllegalArgumentException("Department is full");
        }
        studentRepository.save(student);
        // update information of new department of student

        return StudentMapper.INSTANCE.toStudentDTO(student);
    }

    @Override
    public List<StudentDTO> getAllStudent() {
        return StudentMapper.INSTANCE.toStudentDTOS(studentRepository.findAll());
    }
}
