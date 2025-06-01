package com.example.SpringSecurity.Controller;

import com.example.SpringSecurity.Model.Student;
import com.example.SpringSecurity.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/")
    public String greet(){
        return "Hello";
    }

    @PostMapping("/Register")
    public ResponseEntity<String> Register(@RequestBody Student student) {
        studentService.registerStudent(student);
        return ResponseEntity.ok("Student Registered Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody Student student) {
        Map<String,String> ans = studentService.verifystudent(student);
        if(ans.containsKey("jwt")) {
            return ResponseEntity.ok(ans);
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ans);
        }
    }

}
