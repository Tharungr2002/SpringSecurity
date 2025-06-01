package com.example.SpringSecurity.SecurityConfig;

import com.example.SpringSecurity.Model.Student;
import com.example.SpringSecurity.Repo.StudentRepo;
import com.example.SpringSecurity.Service.jwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class addJwtFilter extends OncePerRequestFilter {

    @Autowired
    private jwtService jwtservice;

    @Autowired
    private StudentRepo studentrepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String username = null;
        String extractedtoken = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")) {
             extractedtoken = authHeader.substring(7);
            username = jwtservice.extractFromToken(extractedtoken);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            Optional<Student> student = studentrepo.findByUsername(username);

            if(student.isPresent() && jwtservice.validateToken(extractedtoken)) {
                UsernamePasswordAuthenticationToken auth = new
                        UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }

}
