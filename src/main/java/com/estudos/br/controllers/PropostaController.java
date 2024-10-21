package com.estudos.br.controllers;

import com.estudos.br.dtos.PropostaRequestDTO;
import com.estudos.br.services.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    @Autowired
    private PropostaService service;

    @PostMapping
    public ResponseEntity criar(@RequestBody PropostaRequestDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

}
