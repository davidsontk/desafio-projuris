package com.desafio.projuris.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "status_ordem")
public class StatusOrdem implements Serializable {

    @Id
    private Integer id;

    @Column(length = 50)
    private String nome;

    public StatusOrdem() {
    }

    public StatusOrdem(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
