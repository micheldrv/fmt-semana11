package com.darochavieira.fmtsemana11.dto;

import com.darochavieira.fmtsemana11.entity.Nota;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaDto {
    private Long id;
    private String titulo;
    private String conteudo;
    private Long cadernoId;

    public NotaDto(Nota nota) {
        this.id = nota.getId();
        this.titulo = nota.getTitulo();
        this.conteudo = nota.getConteudo();
        this.cadernoId = nota.getCaderno().getId();
    }

    public Nota toEntity() {
        Nota nota = new Nota();
        nota.setId(this.getId());
        nota.setTitulo(this.getTitulo());
        nota.setConteudo(this.getConteudo());
        return nota;
    }

    public static List<NotaDto> from(List<Nota> notas) {
        List<NotaDto> lista = new ArrayList<>();
        for (Nota nota : notas) {
            lista.add(new NotaDto(nota));
        }
        return lista;
    }
}
