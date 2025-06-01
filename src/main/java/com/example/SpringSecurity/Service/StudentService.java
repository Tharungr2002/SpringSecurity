package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.Model.Student;
import com.example.SpringSecurity.Repo.StudentRepo;
import com.example.SpringSecurity.SecurityConfig.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private jwtService jwtservice;

    @Autowired
    StudentRepo studentrepo;
    @Autowired
    PasswordEncoder passwordencoder;

    public void registerStudent(Student student) {
        String bcryptPassword = passwordencoder.encode(student.getPassword());
        student.setPassword(bcryptPassword);
        studentrepo.save(student);
    }

    public Map<String,String> verifystudent(Student student) {
        String realPassword = student.getPassword();
        String username = student.getUsername();
        try {
            Optional<Student> optionalStudent = studentrepo.findByUsername(username);
            String token = null;
            if (optionalStudent != null) {
                if (passwordencoder.matches(realPassword, optionalStudent.get().getPassword())) {
                    token = jwtservice.generateToken(student.getUsername());
                    return Map.of("jwt", token);
                }
            }
        }catch(Exception e){
            return Map.of("Error",e.getMessage());

        }
        return Map.of("Error","User not found");
    }
}
