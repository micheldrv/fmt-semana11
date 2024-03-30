package com.darochavieira.fmtsemana11.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "caderno")
public class Caderno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String nome;
}
