package com.darochavieira.fmtsemana11.dto;

import com.darochavieira.fmtsemana11.entity.Caderno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CadernoDto {
    private Long id;
    private String nome;

    public CadernoDto(Caderno caderno) {
        this.id = caderno.getId();
        this.nome = caderno.getNome();
    }

    public Caderno toEntity() {
        Caderno caderno = new Caderno();
        caderno.setId(this.getId());
        caderno.setNome(this.getNome());
        return caderno;
    }

    public static List<CadernoDto> from(List<Caderno> cadernos) {
        List<CadernoDto> lista = new ArrayList<>();
        for (Caderno caderno : cadernos) {
            lista.add(new CadernoDto(caderno));
        }
        return lista;
    }
}
