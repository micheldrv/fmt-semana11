package com.darochavieira.fmtsemana11.service.interfaces;

import com.darochavieira.fmtsemana11.entity.Nota;

import java.util.List;

public interface NotaService {
    List<Nota> findAllByUsuarioId(Long usuarioId);
    List<Nota> findAllByCadernoId(Long cadernoId);
    List<Nota> findAll();
    Nota findById(Long id);
    Nota save(Nota nota);
    Nota update(Long id, Nota nota);
    void deleteById(Long id);
}
