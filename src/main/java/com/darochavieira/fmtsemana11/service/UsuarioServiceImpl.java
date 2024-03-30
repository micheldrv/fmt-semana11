package com.darochavieira.fmtsemana11.service;

import com.darochavieira.fmtsemana11.dto.LoginRequest;
import com.darochavieira.fmtsemana11.entity.Usuario;
import com.darochavieira.fmtsemana11.repository.UsuarioRepository;
import com.darochavieira.fmtsemana11.service.interfaces.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(
            () -> new RuntimeException("Usuário não encontrado.")
        );
    }

    @Override
    public Usuario getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findByEmail(userDetails.getUsername());
    }

    @Override
    public void save(Usuario usuario) {
        usuario.setId(null);

        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado.");
        }

        // TODO: encriptar senha

        usuarioRepository.save(usuario);
    }

    public void deleteCurrentUser() {
        Usuario usuario = getCurrentUser();
        usuarioRepository.deleteById(usuario.getId());
    }
}
