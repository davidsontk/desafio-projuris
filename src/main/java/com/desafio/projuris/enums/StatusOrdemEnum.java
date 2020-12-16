package com.desafio.projuris.enums;

import com.desafio.projuris.model.StatusOrdem;
import lombok.Getter;

@Getter
public enum StatusOrdemEnum {
    ABERTO(1, "ABERTO"),
    EM_ATENDIMENTO(2, "EM_ATENDIMENTO"),
    PENDENTE(3, "PENDENTE"),
    FECHADO(4, "FECHADO");

    private StatusOrdem statusOrdem;

    StatusOrdemEnum(Integer id, String nome) {
        this.statusOrdem = new StatusOrdem(id, nome);
    }
}
