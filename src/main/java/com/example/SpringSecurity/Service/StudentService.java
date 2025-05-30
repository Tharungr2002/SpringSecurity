package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.Model.Student;
import com.example.SpringSecurity.Repo.StudentRepo;
import com.example.SpringSecurity.SecurityConfig.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentrepo;
    @Autowired
    PasswordEncoder passwordencoder;

    public void registerStudent(Student student) {
        String bcryptPassword = passwordencoder.encode(student.getPassword());
        student.setPassword(bcryptPassword);
        studentrepo.save(student);
    }

    public boolean verifystudent(Student student) {
        String realPassword = student.getPassword();
        int studentId = student.getId();
        Optional<Student> optionalStudent = studentrepo.findById(studentId);
        if(optionalStudent != null) {
            passwordencoder.matches(realPassword,optionalStudent.get().getPassword());
        }
        else {
            return false;
        }
        return true;
    }
}
