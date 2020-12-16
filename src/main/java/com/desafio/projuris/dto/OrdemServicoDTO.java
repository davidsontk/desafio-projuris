package com.desafio.projuris.dto;

import lombok.Data;

@Data
public class OrdemServicoDTO {

    private ClienteDTO cliente;
    private String tipo;
    private String marca;
    private String descricao;
}
