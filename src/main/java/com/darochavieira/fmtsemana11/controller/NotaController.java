package com.darochavieira.fmtsemana11.controller;

import com.darochavieira.fmtsemana11.dto.NotaDto;
import com.darochavieira.fmtsemana11.entity.Nota;
import com.darochavieira.fmtsemana11.service.interfaces.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
@RequiredArgsConstructor
public class NotaController {
    private final NotaService notaService;

    @GetMapping
    public ResponseEntity<List<NotaDto>> findAll() {
        List<Nota> notas = notaService.findAll();

        HttpStatus status = HttpStatus.OK;
        return ResponseEntity.status(status).body(NotaDto.from(notas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaDto> findById(@PathVariable Long id) {
        Nota nota = notaService.findById(id);

        HttpStatus status = HttpStatus.OK;
        return ResponseEntity.status(status).body(new NotaDto(nota));
    }

    @GetMapping("/caderno/{cadernoId}")
    public ResponseEntity<List<NotaDto>> findAllByCadernoId(@PathVariable Long cadernoId) {
        List<Nota> notas = notaService.findAllByCadernoId(cadernoId);

        HttpStatus status = HttpStatus.OK;
        return ResponseEntity.status(status).body(NotaDto.from(notas));
    }

    @PostMapping
    public ResponseEntity<NotaDto> save(@RequestBody NotaDto notaDto) {
        Long cadernoId = notaDto.getCadernoId();
        Nota nota = notaService.save(notaDto.toEntity(), cadernoId);

        HttpStatus status = HttpStatus.CREATED;
        return ResponseEntity.status(status).body(new NotaDto(nota));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaDto> update(
            @PathVariable Long id,
            @RequestBody NotaDto notaDto
    ) {
        Long cadernoId = notaDto.getCadernoId();
        Nota nota = notaService.update(id, notaDto.toEntity(), cadernoId);

        HttpStatus status = HttpStatus.OK;
        return ResponseEntity.status(status).body(new NotaDto(nota));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        notaService.deleteById(id);

        HttpStatus status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status).build();
    }
}
