package com.example.studentenrollment.repository;

import com.example.studentenrollment.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    //@Query("select u from user u where u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);
}
