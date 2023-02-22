package com.example.eventos.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        CredencialesUsuario credenciales = new CredencialesUsuario();

        try {
            credenciales = new ObjectMapper().readValue(request.getReader(), CredencialesUsuario.class);
        } catch (Exception e) {

        }
        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                credenciales.getUsuario(), credenciales.getPassword(), Collections.EMPTY_LIST
        );
        return getAuthenticationManager().authenticate(usernamePAT);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        var detallesUsuario = (DetallesUsuarioImpl) authResult.getPrincipal();
        String token = TokenUtils.crearToken(detallesUsuario.getUsername(), detallesUsuario.getNombreCompleto(), detallesUsuario);

        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().write("{"+"'Authorization': 'Bearer " + token+"'}");
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }

}
