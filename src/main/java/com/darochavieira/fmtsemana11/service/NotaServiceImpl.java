package com.darochavieira.fmtsemana11.service;

import com.darochavieira.fmtsemana11.entity.Caderno;
import com.darochavieira.fmtsemana11.entity.Nota;
import com.darochavieira.fmtsemana11.entity.Usuario;
import com.darochavieira.fmtsemana11.repository.NotaRepository;
import com.darochavieira.fmtsemana11.service.interfaces.CadernoService;
import com.darochavieira.fmtsemana11.service.interfaces.NotaService;
import com.darochavieira.fmtsemana11.service.interfaces.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotaServiceImpl implements NotaService {
    private final NotaRepository notaRepository;
    private final UsuarioService usuarioService;
    private final CadernoService cadernoService;

    @Override
    public List<Nota> findAllByUsuarioId(Long usuarioId) {
        return notaRepository.findAllByUsuarioId(usuarioId);
    }

    @Override
    public List<Nota> findAllByCadernoId(Long cadernoId) {
        cadernoService.findById(cadernoId);
        Usuario usuario = usuarioService.getCurrentUser();
        return notaRepository.findAllByCadernoIdAndUsuarioId(cadernoId, usuario.getId());
    }

    @Override
    public List<Nota> findAll() {
        Usuario usuario = usuarioService.getCurrentUser();
        return findAllByUsuarioId(usuario.getId());
    }

    @Override
    public Nota findById(Long id) {
        Usuario usuario = usuarioService.getCurrentUser();

        return notaRepository.findByIdAndUsuarioId(id, usuario.getId()).orElseThrow(
            () -> new RuntimeException("Nota não encontrada.")
        );
    }

    @Override
    public Nota save(Nota nota, Long cadernoId) {
        Usuario usuario = usuarioService.getCurrentUser();
        Caderno caderno = cadernoService.findById(cadernoId);

        nota.setId(null);
        nota.setUsuario(usuario);
        nota.setCaderno(caderno);

        return notaRepository.save(nota);
    }

    @Override
    public Nota update(Long id, Nota nota,Long cadernoId) {
        Usuario usuario = usuarioService.getCurrentUser();
        Caderno caderno = cadernoService.findById(cadernoId);

        findById(id);
        nota.setId(id);
        nota.setUsuario(usuario);
        nota.setCaderno(caderno);

        return notaRepository.save(nota);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        notaRepository.deleteById(id);
    }
}
