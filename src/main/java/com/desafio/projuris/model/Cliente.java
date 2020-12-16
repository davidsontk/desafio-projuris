package com.desafio.projuris.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;

    @Column(length = 150)
    private String email;

    @ManyToOne
    @JoinColumn(name= "endereco_id", referencedColumnName = "id")
    private EnderecoCliente endereco;
}
