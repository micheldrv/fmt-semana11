package com.darochavieira.fmtsemana11.controller;

import com.darochavieira.fmtsemana11.dto.LoginRequest;
import com.darochavieira.fmtsemana11.entity.Usuario;
import com.darochavieira.fmtsemana11.service.interfaces.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = new Usuario();

        usuario.setEmail(loginRequest.email());
        usuario.setSenha(loginRequest.senha());

        usuarioService.save(usuario);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        var authToken = new UsernamePasswordAuthenticationToken(
                loginRequest.email(),
                loginRequest.senha()
        );

        // TODO: authenticate login
        // TODO: return jwt token

        return ResponseEntity.ok().build();
    }

    @PostMapping("/deletar")
    public ResponseEntity<?> deletar(@RequestBody Long id) {
        // TODO
        return ResponseEntity.noContent().build();
    }
}
