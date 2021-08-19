package com.todo.todoapi.data.config;

import com.todo.todoapi.data.model.LoginDetails;
import com.todo.todoapi.data.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

import static com.todo.todoapi.data.model.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;
import static com.todo.todoapi.data.model.Constants.SIGNING_KEY;

@Component
public class JwtTokenUtil implements Serializable {
    public String getUserEmailFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user){
        return doGenerateToken(user.getUserEmail());
    }

    private String doGenerateToken(String email){
        Claims claims = Jwts.claims().setSubject(email);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date((System.currentTimeMillis()) + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    public Boolean validateToken(String token, LoginDetails loginDetails){
        final String userEmail = getUserEmailFromToken(token);
        return(
                userEmail.equals(loginDetails.getUserEmail())
                        && !isTokenExpired(token)
                );
    }
}
