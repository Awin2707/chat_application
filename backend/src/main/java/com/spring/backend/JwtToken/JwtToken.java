package com.spring.backend.JwtToken;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtToken {

    @Value("${spring.token}")
    private String jwt_Token;

    private SecretKey generateSecretKey(){
        return Keys.hmacShaKeyFor(jwt_Token.getBytes());
    }

    public String generateToken(String email){
           return Jwts.builder()
                   .signWith(generateSecretKey(), SignatureAlgorithm.HS256)
                   .issuedAt(new Date())
                   .expiration(new Date(System.currentTimeMillis() + 604800 * 60))
                   .subject(email)
                   .compact();
    }

    public String extractUserName(String token){
        return Jwts.parser()
                .verifyWith(generateSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isValidToken(String token){
        String userName = extractUserName(token);
        return !userName.isEmpty() && isExpiredToken(token);
    }

    public boolean isExpiredToken(String token){
        Date date =  Jwts.parser()
                .verifyWith(generateSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return new Date().before(date);
    }
}
