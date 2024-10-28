package com.estudos.br.services;

import com.estudos.br.dtos.PropostaRequestDTO;
import com.estudos.br.dtos.PropostaResponseDTO;
import com.estudos.br.entities.Proposta;
import com.estudos.br.mapper.PropostaMapper;
import com.estudos.br.repositories.PropostaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropostaService {

    private final PropostaRepository repository;
    private final PropostaMapper mapper;
    private final NotificacaoService notificacaoService;

    public PropostaResponseDTO criar (PropostaRequestDTO dto) {
        Proposta proposta = mapper.convertDTOToProposta(dto);
        repository.save(proposta);

        PropostaResponseDTO response = mapper.convertEntityToDTO(proposta);
        notificacaoService.notificar(response, "proposta-pendente.ex");

        return response;
    }

    public List<PropostaResponseDTO> obterProposta() {
        return mapper.convertListEntityToListDTO(repository.findAll());
    }

}
