package org.demoworks.movie.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;



@Component
public class JWTUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Value("${jwt.constants.secret}")
    private String secret;



    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).get("username", String.class);
    }

}