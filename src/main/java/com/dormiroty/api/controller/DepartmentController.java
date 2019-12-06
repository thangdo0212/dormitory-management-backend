package com.dormiroty.api.controller;

import com.dormiroty.api.model.dto.DepartmentDTO;
import com.dormiroty.api.model.request.DeparmentRequest;
import com.dormiroty.api.model.response.RestResult;
import com.dormiroty.api.service.DepartmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {

  @Autowired
  private DepartmentService departmentService;

  @GetMapping()
  public ResponseEntity<RestResult<List<DepartmentDTO>>> getAllDepartment() {
    RestResult<List<DepartmentDTO>> restResult = new RestResult<>();
    restResult.setData(departmentService.findAll());
    restResult.setMessage("Get all success");
    return new ResponseEntity<>(restResult, HttpStatus.OK);
  }

  @GetMapping("/null-department")
  public ResponseEntity<RestResult<List<DepartmentDTO>>> getAllNullDepartment() {
    RestResult<List<DepartmentDTO>> restResult = new RestResult<>();
    restResult.setData(departmentService.findAllNullDepartment());
    restResult.setMessage("Get all null department success");
    return new ResponseEntity<>(restResult, HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<RestResult<DepartmentDTO>> createDepartment(
      @RequestBody DeparmentRequest deparmentRequest
  ) {
    RestResult<DepartmentDTO> restResult = new RestResult<>();
    restResult.setData(departmentService.createDepartment(deparmentRequest));
    restResult.setMessage("Create department success");
    return new ResponseEntity<>(restResult, HttpStatus.OK);
  }

  @PutMapping()
  public ResponseEntity<RestResult<DepartmentDTO>> updateDepartment(
      @RequestBody DeparmentRequest deparmentRequest
  ) {
    RestResult<DepartmentDTO> restResult = new RestResult<>();
    restResult.setData(departmentService.updateDepartment(deparmentRequest));
    restResult.setMessage("Update department success");
    return new ResponseEntity<>(restResult, HttpStatus.OK);
  }
}
