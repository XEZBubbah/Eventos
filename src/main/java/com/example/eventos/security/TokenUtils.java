package com.example.eventos.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class TokenUtils {

    private final static String PASSWORD = "e10adc3949ba59abbe56e057f20f883e";
    private final static Long VIGENCIA = 3600L * 1000;


    public static String crearToken (String nickname, String fullname) {
        Date fechaExpiracion = new Date(System.currentTimeMillis() + VIGENCIA);
        Map<String, Object> informacionExtra = new HashMap<>();
        informacionExtra.put("nickname", nickname);
        informacionExtra.put("fullname", fullname);
        return Jwts.builder()
                .setSubject(nickname)
                .setExpiration(fechaExpiracion)
                .addClaims(informacionExtra)
                .signWith(Keys.hmacShaKeyFor(PASSWORD.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken interceptor(String token) {
        try {
            Claims data = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(PASSWORD.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String nickname = data.getSubject();

            return new UsernamePasswordAuthenticationToken(nickname, null, Collections.EMPTY_LIST);
        }catch (Exception e) {
            return null;
        }
    }
}
