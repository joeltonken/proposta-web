package com.estudos.br.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_PROPOSTA")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_solicitado")
    private Double valorSolicitado;

    private int prazoPagamento;

    private Boolean aprovada; // Null

    private boolean integrada; // false

    private String observacao;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}