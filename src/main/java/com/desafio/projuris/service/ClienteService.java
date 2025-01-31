package com.desafio.projuris.service;

import com.desafio.projuris.dto.ClienteDTO;
import com.desafio.projuris.mapper.ClienteMapper;
import com.desafio.projuris.mapper.EnderecoClienteMapper;
import com.desafio.projuris.model.Cliente;
import com.desafio.projuris.model.EnderecoCliente;
import com.desafio.projuris.repository.ClienteRepository;
import com.desafio.projuris.repository.EnderecoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private EnderecoClienteMapper enderecoClienteMapper;

    @Autowired
    private EnderecoClienteRepository enderecoClienteRepository;

    /**
     * Metodo registra um cliente, levando em conta que o email nao deve ser o mesmo. Caso o cliente ja exista,
     * retorna ele.
     * @param cliente
     * @return Cliente
     */
    public Cliente registrarCliente(Cliente cliente) {
        Optional<Cliente> clienteAux = clienteRepository.findByEmailIgnoreCase(cliente.getEmail().toLowerCase());
        if (clienteAux.isPresent()) {
            return clienteAux.get();
        }
        cliente.setEndereco(registrarEndereco(cliente.getEndereco()));
        return clienteRepository.save(cliente);
    }

    /**
     * Metodo converte um clienteDTO para um Cliente.
     * @param clienteDTO
     * @return Cliente
     */
    public Cliente converterParaCliente(ClienteDTO clienteDTO) {
        EnderecoCliente enderecoCliente = enderecoClienteMapper.converterParaEnderecoCliente(clienteDTO.getEndereco());
        return clienteMapper.converterParaCliente(clienteDTO, enderecoCliente);
    }

    /**
     * Metodo salva o endereco de um cliente e retorna ele.
     * @param enderecoCliente
     * @return EnderecoCliente
     */
    public EnderecoCliente registrarEndereco(EnderecoCliente enderecoCliente) {
        return enderecoClienteRepository.save(enderecoCliente);
    }

}
