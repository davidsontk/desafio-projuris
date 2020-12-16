package com.desafio.projuris.handler.exceptions;

import com.desafio.projuris.enums.ErroCodeEnum;
import lombok.Data;

@Data
public class ExcecaoNegocio extends RuntimeException{
    private ErroCodeEnum error;


    public ExcecaoNegocio(ErroCodeEnum errorCode) {
        this.error = errorCode;
    }

}
