package com.desafio.projuris.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorCode {
    private HttpStatus statusCode;
    private String mensagem;

    public ErrorCode(HttpStatus statusCode, String mensagem) {
        this.statusCode = statusCode;
        this.mensagem = mensagem;
    }
}
