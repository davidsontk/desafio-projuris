package com.desafio.projuris.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "ordem_servico")
public class OrdemServico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 100)
    private String responsavel;

    @ManyToOne
    @JoinColumn(name= "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @Column(length = 150)
    private String tipo;

    @Column(length = 150)
    private String marca;

    @Column(length = 500)
    private String descricao;

    @Column(name = "inicio_atendimento")
    private Date inicioAtendimento;

    @Column(name = "final_atendimento")
    private Date finalAtendimento;

    @Column(name = "descricao_resolucao", length = 500)
    private String descricaoResolucao;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private StatusOrdem status;
}
