package com.dormiroty.api.repository;

import com.dormiroty.api.model.entity.Student;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, ObjectId> {
  List<Student> findAllByDepartmentName(String departmentName);
}
