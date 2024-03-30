package com.darochavieira.fmtsemana11.service;

import com.darochavieira.fmtsemana11.repository.UsuarioRepository;
import com.darochavieira.fmtsemana11.service.interfaces.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email).orElseThrow(
            () -> new UsernameNotFoundException("Usuário não encontrado.")
        );
    }
}
