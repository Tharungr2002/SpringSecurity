package com.example.SpringSecurity.Controller;

import com.example.SpringSecurity.Model.Student;
import com.example.SpringSecurity.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/")
    public String greet(){
        return "Hello";
    }

    @PostMapping("/Register")
    public String Register(@RequestBody Student student) {
        studentService.registerStudent(student);
        return "Successfully registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody Student student) {
        if(studentService.verifystudent(student)) return "Successfully verified";
        return "please register";
    }

}
