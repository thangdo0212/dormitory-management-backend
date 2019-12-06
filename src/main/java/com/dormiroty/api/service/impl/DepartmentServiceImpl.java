package com.dormiroty.api.service.impl;

import com.dormiroty.api.model.dto.DepartmentDTO;
import com.dormiroty.api.model.entity.Department;
import com.dormiroty.api.model.mapper.DepartmentMapper;
import com.dormiroty.api.model.request.DeparmentRequest;
import com.dormiroty.api.repository.DepartmentRepository;
import com.dormiroty.api.repository.StudentRepository;
import com.dormiroty.api.service.DepartmentService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

  @Autowired
  private DepartmentRepository departmentRepository;

  @Autowired
  private StudentRepository studentRepository;

  @Override
  public List<DepartmentDTO> findAll() {
    List<Department> departments = departmentRepository.findAll();
    return DepartmentMapper.INSTANCE.toDepartmentDTOS(departments);
  }

  @Override
  public List<DepartmentDTO> findAllNullDepartment() {
    return DepartmentMapper.INSTANCE.toDepartmentDTOS(departmentRepository.findAllByNullBedIsLessThanEqual(8));
  }

  @Override
  public DepartmentDTO createDepartment(DeparmentRequest deparmentRequest) {
    Department department = new Department();
    department.setName(deparmentRequest.getName());
    department.setTotalBed(8);
    department.setNullBed(8);
    department.setStudents(new ArrayList<>());
    departmentRepository.save(department);
    return DepartmentMapper.INSTANCE.toDepartmentDTO(department);
  }

  @Override
  public DepartmentDTO updateDepartment(DeparmentRequest deparmentRequest) {
    Department department = departmentRepository.findById(deparmentRequest.getObjectId()).get();
    if(deparmentRequest.getStudents().size() <= 8) {
      department.setStudents(deparmentRequest.getStudents());
    } else {
      throw new IllegalArgumentException("Phòng chỉ cho 8 người ở");
    }
    department.setNullBed(department.getStudents().size());
    departmentRepository.save(department);
    studentRepository.saveAll(deparmentRequest.getStudents());
    return DepartmentMapper.INSTANCE.toDepartmentDTO(department);
  }
}
