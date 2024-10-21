package com.estudos.br.services;

import com.estudos.br.dtos.PropostaRequestDTO;
import com.estudos.br.dtos.PropostaResponseDTO;
import com.estudos.br.entities.Proposta;
import com.estudos.br.mapper.PropostaMapper;
import com.estudos.br.repositories.PropostaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropostaService {

    private final PropostaRepository repository;

    public PropostaResponseDTO criar (PropostaRequestDTO dto) {
        Proposta proposta = PropostaMapper.INSTANCE.convertDTOToProposta(dto);
        repository.save(proposta);

        return PropostaMapper.INSTANCE.convertEntityToDTO(proposta);
    }

}
