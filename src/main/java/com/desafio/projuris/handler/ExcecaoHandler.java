package com.desafio.projuris.handler;

import com.desafio.projuris.enums.ErroCodeEnum;
import com.desafio.projuris.handler.exceptions.ExcecaoNegocio;
import com.desafio.projuris.model.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExcecaoHandler {

    @ExceptionHandler(ExcecaoNegocio.class)
    public ResponseEntity<ErrorCode> interceptarRequisicao(ExcecaoNegocio excecaoNegocio) {
        ErroCodeEnum error = excecaoNegocio.getError();
        return new ResponseEntity<>(error.getErrorCode(), error.getErrorCode().getStatusCode());
    }


}
