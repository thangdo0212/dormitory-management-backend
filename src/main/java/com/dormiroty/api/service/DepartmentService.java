package com.dormiroty.api.service;

import com.dormiroty.api.model.dto.DepartmentDTO;
import com.dormiroty.api.model.request.DeparmentRequest;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {

  List<DepartmentDTO> findAll();

  List<DepartmentDTO> findAllNullDepartment();

  DepartmentDTO createDepartment(DeparmentRequest deparmentRequest);

  DepartmentDTO updateDepartment(DeparmentRequest deparmentRequest);
}
