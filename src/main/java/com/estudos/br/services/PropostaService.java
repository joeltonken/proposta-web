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

    public PropostaResponseDTO criar (PropostaRequestDTO dto) {
        Proposta proposta = mapper.convertDTOToProposta(dto);
        repository.save(proposta);

        return mapper.convertEntityToDTO(proposta);
    }

    public List<PropostaResponseDTO> obterProposta() {
        return mapper.convertListEntityToListDTO(repository.findAll());
    }

}
