package com.example.eventos.security;

import com.example.eventos.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class BuscadorUsuario implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = userRepository.findOneByUser(username)
                .orElseThrow(()-> new UsernameNotFoundException("El usuario no existe"));
        return new DetallesUsuarioImpl(usuario);
    }
}
