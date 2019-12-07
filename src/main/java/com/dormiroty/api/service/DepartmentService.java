package com.dormiroty.api.service;

import com.dormiroty.api.model.dto.DepartmentDTO;
import com.dormiroty.api.model.request.DepartmentRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    DepartmentDTO createDepartment(DepartmentRequest departmentRequest);

    List<DepartmentDTO> getAllDepartment(Boolean isNullDepartment);

    DepartmentDTO searchDepartmentByName(String departmentName);
}
