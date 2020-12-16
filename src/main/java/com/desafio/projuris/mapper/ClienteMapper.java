package com.desafio.projuris.mapper;

import com.desafio.projuris.dto.ClienteDTO;
import com.desafio.projuris.model.Cliente;
import com.desafio.projuris.model.EnderecoCliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {


    public Cliente converterParaCliente(ClienteDTO clienteDTO, EnderecoCliente enderecoCliente) {
        Cliente cliente = new Cliente();
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setNome(clienteDTO.getNome());
        cliente.setEndereco(enderecoCliente);
        return cliente;
    }
}
