package com.myshopping.ShopHub.Security;


import com.myshopping.ShopHub.Entity.AppUsers;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class Token {
    @Value("${jwt.key}")
    private String secretkey;

    public SecretKey getKey(){
        return Keys.hmacShaKeyFor(secretkey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(AppUsers user){
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("User id",user.getUserid().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getKey())
                .compact();
    }
    public String getUsernameFromToken(String token){
        Claims claims=Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }



}
