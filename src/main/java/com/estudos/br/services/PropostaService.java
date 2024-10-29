package com.estudos.br.services;

import com.estudos.br.dtos.PropostaRequestDTO;
import com.estudos.br.dtos.PropostaResponseDTO;
import com.estudos.br.entities.Proposta;
import com.estudos.br.mapper.PropostaMapper;
import com.estudos.br.repositories.PropostaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropostaService {

    private final PropostaRepository repository;
    private final PropostaMapper mapper;
    private final NotificacaoService notificacaoService;

    @Value("${rabbitmq.propostapendente.exchange}")
    private String exchange;

    public PropostaResponseDTO criar (PropostaRequestDTO dto) {
        Proposta proposta = mapper.convertDTOToProposta(dto);
        repository.save(proposta);

        PropostaResponseDTO response = mapper.convertEntityToDTO(proposta);
        notificacaoService.notificar(response, exchange);

        return response;
    }

    public List<PropostaResponseDTO> obterProposta() {
        return mapper.convertListEntityToListDTO(repository.findAll());
    }

}
