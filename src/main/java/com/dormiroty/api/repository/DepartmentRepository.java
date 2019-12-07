package com.dormiroty.api.repository;

import com.dormiroty.api.model.entity.Department;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, ObjectId> {

    List<Department> findAllByCurrentPeoplesLessThan(Integer maxPeoples);

    Department findByDepartmentName(String departmentName);
}
