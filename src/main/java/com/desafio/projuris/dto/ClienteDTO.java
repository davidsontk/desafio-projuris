package com.desafio.projuris.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private String nome;
    private String telefone;
    private String email;
    private EnderecoClienteDTO endereco;
}
