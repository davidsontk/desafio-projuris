package com.desafio.projuris.enums;

import com.desafio.projuris.model.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErroCodeEnum {
    ORDEM_SERVICO_NAO_ENCONTRADA(HttpStatus.NO_CONTENT, "Ordem de serviço não encontrada"),
    ERRO_AO_SALVAR_ORDEM_SERVICO(HttpStatus.INTERNAL_SERVER_ERROR , "Erro ao tentar salvar ordem de serviço"),
    ERRO_AO_SALVAR_INICIO_ATENDIMENTO(HttpStatus.INTERNAL_SERVER_ERROR , "Erro ao salvar inicio de atendimento"),
    ERRO_AO_SALVAR_FINAL_ATENDIMENTO(HttpStatus.INTERNAL_SERVER_ERROR , "Erro ao salvar final de atendimento"),
    ERRO_AO_SALVAR_PENDENCIA(HttpStatus.INTERNAL_SERVER_ERROR , "Erro ao salvar pendencia em ordem de servico");


    ;


    private ErrorCode errorCode;

    ErroCodeEnum(HttpStatus status, String s) {
        this.errorCode = new ErrorCode(status, s);
    }
}
