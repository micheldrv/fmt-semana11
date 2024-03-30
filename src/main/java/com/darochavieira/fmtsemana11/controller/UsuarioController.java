package com.darochavieira.fmtsemana11.controller;

import com.darochavieira.fmtsemana11.dto.JwtDto;
import com.darochavieira.fmtsemana11.dto.LoginRequest;
import com.darochavieira.fmtsemana11.entity.Usuario;
import com.darochavieira.fmtsemana11.service.interfaces.JwtService;
import com.darochavieira.fmtsemana11.service.interfaces.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = new Usuario();

        usuario.setEmail(loginRequest.email());
        usuario.setSenha(loginRequest.senha());
        usuarioService.save(usuario);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody LoginRequest loginRequest) {
        var authToken = new UsernamePasswordAuthenticationToken(
                loginRequest.email(),
                loginRequest.senha()
        );

        authenticationManager.authenticate(authToken);

        JwtDto jwt = jwtService.gerarToken(loginRequest.email());
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/deletar")
    public ResponseEntity<?> deletar(@RequestBody Long id) {
        // TODO
        return ResponseEntity.noContent().build();
    }
}
