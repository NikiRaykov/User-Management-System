package com.example.usermanagement.repository;

import com.example.usermanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllUsersSortedByLastNameAndDateOfBirth(String lastName, Date dateOfBirth);
    List<User> findByFirstName(String firstName);
}
