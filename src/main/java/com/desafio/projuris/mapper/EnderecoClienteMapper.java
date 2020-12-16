package com.desafio.projuris.mapper;

import com.desafio.projuris.dto.EnderecoClienteDTO;
import com.desafio.projuris.model.EnderecoCliente;
import org.springframework.stereotype.Component;

@Component
public class EnderecoClienteMapper {

    public EnderecoCliente converterParaEnderecoCliente(EnderecoClienteDTO enderecoClienteDTO) {
        EnderecoCliente enderecoCliente = new EnderecoCliente();
        enderecoCliente.setNumero(enderecoClienteDTO.getNumero());
        enderecoCliente.setRua(enderecoClienteDTO.getRua());

        return enderecoCliente;
    }
}
