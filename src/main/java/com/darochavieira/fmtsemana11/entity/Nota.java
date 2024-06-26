package com.darochavieira.fmtsemana11.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "nota")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "caderno_id")
    private Caderno caderno;

    private String titulo;
    private String conteudo;
}
