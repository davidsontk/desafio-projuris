package com.desafio.projuris.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "ordem_servico_pendencia")
public class OrdemServicoPendencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 500)
    private String descricao;

    @Column(name = "data_inicio")
    private Date dataInicio;

    @Column(name = "data_encerramento")
    private Date dataEncerramento;

    @ManyToOne
    @JoinColumn(name= "ordem_servico_id", referencedColumnName = "id")
    private OrdemServico ordemServicoId;
}
