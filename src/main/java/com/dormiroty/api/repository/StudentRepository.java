package com.dormiroty.api.repository;

import com.dormiroty.api.model.entity.Student;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, ObjectId> {

    Student findByStudentCode(String studentCode);

    void deleteByStudentCode(String studentCode);
}
