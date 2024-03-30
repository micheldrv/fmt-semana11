package com.darochavieira.fmtsemana11.service;

import com.darochavieira.fmtsemana11.entity.Caderno;
import com.darochavieira.fmtsemana11.entity.Usuario;
import com.darochavieira.fmtsemana11.repository.CadernoRepository;
import com.darochavieira.fmtsemana11.service.interfaces.CadernoService;
import com.darochavieira.fmtsemana11.service.interfaces.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CadernoServiceImpl implements CadernoService {
    private final CadernoRepository cadernoRepository;
    private final UsuarioService usuarioService;

    @Override
    public List<Caderno> findAllByUsuarioId(Long usuarioId) {
        return cadernoRepository.findAllByUsuarioId(usuarioId);
    }

    @Override
    public List<Caderno> findAll() {
        Usuario usuario = usuarioService.getCurrentUser();
        return findAllByUsuarioId(usuario.getId());
    }

    @Override
    public Caderno findById(Long id) {
        Usuario usuario = usuarioService.getCurrentUser();

        return cadernoRepository.findByIdAndUsuarioId(id, usuario.getId()).orElseThrow(
            () -> new RuntimeException("Caderno não encontrado.")
        );
    }

    @Override
    public Caderno save(Caderno caderno) {
        Usuario usuario = usuarioService.getCurrentUser();

        caderno.setId(null);
        caderno.setUsuario(usuario);

        return cadernoRepository.save(caderno);
    }

    @Override
    public Caderno update(Long id, Caderno caderno) {
        Usuario usuario = usuarioService.getCurrentUser();

        findById(id);
        caderno.setId(id);
        caderno.setUsuario(usuario);

        return cadernoRepository.save(caderno);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        cadernoRepository.deleteById(id);
    }
}
