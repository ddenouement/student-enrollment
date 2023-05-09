package com.example.studentenrollment.repository;

import com.example.studentenrollment.model.Faculty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends CrudRepository<Faculty, Long> {

}
