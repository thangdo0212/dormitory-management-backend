package com.dormiroty.api.service.impl;

import com.dormiroty.api.model.dto.DepartmentDTO;
import com.dormiroty.api.model.entity.Department;
import com.dormiroty.api.model.mapper.DepartmentMapper;
import com.dormiroty.api.model.request.DepartmentRequest;
import com.dormiroty.api.repository.DepartmentRepository;
import com.dormiroty.api.service.DepartmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDTO createDepartment(DepartmentRequest departmentRequest) {
        Department department = new Department();
        department.setDepartmentName(departmentRequest.getDepartmentName());
        department.setMaxPeoples(8); // default 8 peoples per one department
        department.setCurrentPeoples(0); // the first of create department current peoples is zero
        department.setStudents(new ArrayList<>());
        departmentRepository.save(department);
        return DepartmentMapper.INSTANCE.toDepartmentDTO(department);
    }

    @Override
    public List<DepartmentDTO> getAllDepartment(Boolean isNullDepartment) {
        List<Department> departmentList = null;
        if(isNullDepartment) {
            departmentList = departmentRepository.findAllByCurrentPeoplesLessThan(8);
        } else {
            departmentList = departmentRepository.findAll();
        }
        return DepartmentMapper.INSTANCE.toDepartmentDTOS(departmentList);
    }

    @Override
    public DepartmentDTO searchDepartmentByName(String departmentName) {
        Department department = departmentRepository.findByDepartmentName(departmentName);
        return DepartmentMapper.INSTANCE.toDepartmentDTO(department);
    }
}
