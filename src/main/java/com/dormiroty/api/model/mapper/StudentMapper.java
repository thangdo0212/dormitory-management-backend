package com.dormiroty.api.model.mapper;

import com.dormiroty.api.model.dto.StudentDTO;
import com.dormiroty.api.model.entity.Student;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {

  StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

  StudentDTO toStudentDTO(Student student);
  List<StudentDTO> toStudentDTOS(List<Student> students);
}
