package com.darochavieira.fmtsemana11.repository;

import com.darochavieira.fmtsemana11.entity.Caderno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CadernoRepository extends JpaRepository<Caderno, Long> {
    List<Caderno> findAllByUsuarioId(Long usuarioId);
    Optional<Caderno> findByIdAndUsuarioId(Long id, Long usuarioId);
}
