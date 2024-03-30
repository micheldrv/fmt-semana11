package com.darochavieira.fmtsemana11.repository;

import com.darochavieira.fmtsemana11.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findAllByUsuarioId(Long usuarioId);
    List<Nota> findAllByCadernoId(Long cadernoId);
    Optional<Nota> findByIdAndUsuarioId(Long id, Long usuarioId);
}
