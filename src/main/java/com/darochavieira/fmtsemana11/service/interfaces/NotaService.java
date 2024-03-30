package com.darochavieira.fmtsemana11.service.interfaces;

import com.darochavieira.fmtsemana11.entity.Nota;

import java.util.List;

public interface NotaService {
    List<Nota> findAllByUsuarioId(Long usuarioId);
    List<Nota> findAllByCadernoId(Long cadernoId);
    List<Nota> findAll();
    Nota findById(Long id);
    Nota save(Nota nota, Long cadernoId);
    Nota update(Long id, Nota nota, Long cadernoId);
    void deleteById(Long id);
}
