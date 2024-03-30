package com.darochavieira.fmtsemana11.service.interfaces;


import com.darochavieira.fmtsemana11.dto.LoginRequest;
import com.darochavieira.fmtsemana11.entity.Usuario;

import java.util.Optional;


public interface UsuarioService {
    Usuario findByEmail(String email);
    Usuario getCurrentUser();
    void save(Usuario usuario);
    void deleteCurrentUser();
}
