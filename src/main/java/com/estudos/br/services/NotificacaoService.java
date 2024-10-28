package com.estudos.br.services;

import com.estudos.br.dtos.PropostaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificacaoService {

    private final RabbitTemplate rabbitTemplate;

    public void notificar(PropostaResponseDTO proposta, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", proposta);
    }

}
