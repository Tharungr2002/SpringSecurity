package com.example.SpringSecurity.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    private String username;
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Override
    public String toString() {
        return "Student{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
