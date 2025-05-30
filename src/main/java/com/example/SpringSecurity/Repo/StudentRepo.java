package com.example.SpringSecurity.Repo;

import com.example.SpringSecurity.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {



}
