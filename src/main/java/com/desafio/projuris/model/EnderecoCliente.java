package com.desafio.projuris.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "endereco_cliente")
public class EnderecoCliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 300)
    private String rua;

    private int numero;

}
