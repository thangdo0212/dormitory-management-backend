package com.dormiroty.api.model.mapper;

import com.dormiroty.api.model.entity.Department;
import com.dormiroty.api.model.dto.DepartmentDTO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {

  DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

  DepartmentDTO toDepartmentDTO(Department department);
  List<DepartmentDTO> toDepartmentDTOS(List<Department> departments);
}
