package com.estudos.br.controllers;

import com.estudos.br.dtos.PropostaRequestDTO;
import com.estudos.br.dtos.PropostaResponseDTO;
import com.estudos.br.services.PropostaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proposta")
public class PropostaController {

    private final PropostaService service;

    @PostMapping
    public ResponseEntity criar(@RequestBody PropostaRequestDTO dto) {
        PropostaResponseDTO response = service.criar(dto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri()).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PropostaResponseDTO>> obterProposta() {
        return ResponseEntity.ok(service.obterProposta());
    }

}
