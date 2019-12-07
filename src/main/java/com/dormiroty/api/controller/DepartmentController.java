package com.dormiroty.api.controller;

import com.dormiroty.api.model.dto.DepartmentDTO;
import com.dormiroty.api.model.request.DepartmentRequest;
import com.dormiroty.api.model.response.RestResult;
import com.dormiroty.api.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping()
    public ResponseEntity<RestResult<List<DepartmentDTO>>> getDepartment(@RequestParam("isNullDepartment") Boolean isNullDepartment) {
        RestResult<List<DepartmentDTO>> restResult = new RestResult<>();
        restResult.setData(departmentService.getAllDepartment(isNullDepartment));
        restResult.setMessage("Get departments success");
        return new ResponseEntity<>(restResult, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<RestResult<DepartmentDTO>> createDepartment(@RequestBody DepartmentRequest request) {
        RestResult<DepartmentDTO> restResult = new RestResult<>();
        restResult.setData(departmentService.createDepartment(request));
        restResult.setMessage("Create department success");
        return new ResponseEntity<>(restResult, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<RestResult<DepartmentDTO>> searchDepartment(@RequestParam("departmentName") String departmentName) {
        RestResult<DepartmentDTO> restResult = new RestResult<>();
        restResult.setData(departmentService.searchDepartmentByName(departmentName));
        restResult.setMessage("Search department success");
        return new ResponseEntity<>(restResult, HttpStatus.OK);
    }

}
