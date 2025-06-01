package com.example.SpringSecurity.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class jwtService {

    private String SECRET_KEY = "bdca0452d50eddc4de139e3b1832d950cb9f1e625723a3c8c882597f3f2a0c3815eb43de40659e5daabf8d4a1372ac3555668a5d027a334afa84d1fcca06ebe6083075752375c19694a5890db72f238bec5d2b20f7ad1d369b4ef9a88c2f6e847e8817b8416ef162eb8d3679e7ef6068e883347768706ec2ff28765b3c909ffca169f9b9022ecc589e5abc36b7bcd064734d18b2c043b1071c350491abf403258f7a900300c45bcfc0084f4b00cfc959a8df3d0fd461bd5d344f0ae08669256f9865b1962cf31a029f21fa49cf703f766dd8d0b13c71b5eeda30f22222485969ff7a0573629be102aab412f39a7af169810f40f1d703d12cdc73d5db655382e1";

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
