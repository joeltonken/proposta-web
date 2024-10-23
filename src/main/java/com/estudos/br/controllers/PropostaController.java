package com.estudos.br.controllers;

import com.estudos.br.dtos.PropostaRequestDTO;
import com.estudos.br.dtos.PropostaResponseDTO;
import com.estudos.br.services.PropostaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

}
