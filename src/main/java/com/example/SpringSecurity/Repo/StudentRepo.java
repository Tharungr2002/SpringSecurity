package com.example.SpringSecurity.Repo;

import com.example.SpringSecurity.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {

  Optional<Student> findByUsername(String username);

}
