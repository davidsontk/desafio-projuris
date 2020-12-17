package com.desafio.projuris.dto;

import lombok.Data;

@Data
public class RespostaDTO {
    private String mensagem;

    public RespostaDTO(String mensagem) {
        this.mensagem = mensagem;
    }
}
