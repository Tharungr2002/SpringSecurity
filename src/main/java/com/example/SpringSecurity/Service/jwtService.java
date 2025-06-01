package com.example.SpringSecurity.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class jwtService {

    private String SECRET_KEY = "tharungr2002";

    public String generateToken(String userName) {
        return Jwts.builder().
                setSubject(userName).
                issuedAt(new Date()).
                setExpiration(new Date(System.currentTimeMillis() + 3600000)).
                signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256).
                compact();
    }

    public String extractFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).build().
                parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try{
            Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).build().parseClaimsJws(token);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
