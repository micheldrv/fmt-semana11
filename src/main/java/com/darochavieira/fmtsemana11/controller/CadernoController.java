package com.darochavieira.fmtsemana11.controller;

import com.darochavieira.fmtsemana11.dto.CadernoDto;
import com.darochavieira.fmtsemana11.entity.Caderno;
import com.darochavieira.fmtsemana11.service.interfaces.CadernoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadernos")
@RequiredArgsConstructor
public class CadernoController {
    private final CadernoService cadernoService;

    @GetMapping
    public ResponseEntity<List<CadernoDto>> findAll() {
        List<Caderno> cadernos = cadernoService.findAll();

        HttpStatus status = HttpStatus.OK;
        return ResponseEntity.status(status).body(CadernoDto.from(cadernos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CadernoDto> findById(@PathVariable Long id) {
        Caderno caderno = cadernoService.findById(id);

        HttpStatus status = HttpStatus.OK;
        return ResponseEntity.status(status).body(new CadernoDto(caderno));
    }

    @PostMapping
    public ResponseEntity<CadernoDto> save(@RequestBody CadernoDto cadernoDto) {
        Caderno caderno = cadernoService.save(cadernoDto.toEntity());

        HttpStatus status = HttpStatus.CREATED;
        return ResponseEntity.status(status).body(new CadernoDto(caderno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CadernoDto> update(
            @PathVariable Long id,
            @RequestBody CadernoDto cadernoDto
    ) {
        Caderno caderno = cadernoService.update(id, cadernoDto.toEntity());

        HttpStatus status = HttpStatus.OK;
        return ResponseEntity.status(status).body(new CadernoDto(caderno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        cadernoService.deleteById(id);

        HttpStatus status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status).build();
    }
}
