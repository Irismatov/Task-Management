package uz.pdp.taskmanagement.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import uz.pdp.taskmanagement.entity.UserEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.key}")
    private String key;

    public String generateToken(UserEntity user) {
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .subject(user.getUsername())
                .claims(Map.of("authorities", getRoles(user)))
                .compact();
    }

    private List<String> getRoles(UserEntity user) {
        return user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }

    public Jws<Claims> validateToken(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(key.getBytes()))
                .build()
                .parseSignedClaims(token);
    }
}
