package com.dormiroty.api.repository;

import com.dormiroty.api.model.entity.Department;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, ObjectId> {

  List<Department> findAllByNullBedIsLessThanEqual(Integer nullBeds);
}
