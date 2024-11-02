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
    private final NotificacaoRabbitService notificacaoService;

    @Value("${rabbitmq.propostapendente.exchange}")
    private String exchange;

    public PropostaResponseDTO criar (PropostaRequestDTO dto) {
        Proposta proposta = mapper.convertDTOToProposta(dto);
        repository.save(proposta);

        notificarRabbitMQ(proposta);
        notificacaoService.notificar(proposta, exchange);

        return mapper.convertEntityToDTO(proposta);
    }

    private void notificarRabbitMQ(Proposta proposta) {
        try {
            notificacaoService.notificar(proposta, exchange);
        } catch (RuntimeException e) {
            proposta.setIntegrada(false);
            repository.save(proposta);
        }
    }

    public List<PropostaResponseDTO> obterProposta() {
        return mapper.convertListEntityToListDTO(repository.findAll());
    }

}
