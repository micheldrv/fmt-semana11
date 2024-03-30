package com.darochavieira.fmtsemana11.service.interfaces;

import com.darochavieira.fmtsemana11.entity.Caderno;

import java.util.List;

public interface CadernoService {
    List<Caderno> findAllByUsuarioId(Long usuarioId);
    List<Caderno> findAll();
    Caderno findById(Long id);
    Caderno save(Caderno caderno);
    Caderno update(Long id, Caderno caderno);
    void deleteById(Long id);
}
