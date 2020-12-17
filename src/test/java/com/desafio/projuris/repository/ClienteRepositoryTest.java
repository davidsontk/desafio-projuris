package com.desafio.projuris.repository;

import com.desafio.projuris.model.Cliente;
import com.desafio.projuris.model.EnderecoCliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoClienteRepository enderecoClienteRepository;

    @Test
    void deveSalvarCliente() {
        EnderecoCliente enderecoCliente = new EnderecoCliente();
        enderecoCliente.setRua("Rua teste");
        enderecoCliente.setNumero(10);
        enderecoCliente = enderecoClienteRepository.save(enderecoCliente);

        Cliente cliente = new Cliente();
        cliente.setEndereco(enderecoCliente);
        cliente.setNome("Larina");
        cliente.setEmail("larina@gmail.com");
        cliente = clienteRepository.save(cliente);

        Assertions.assertNotNull(cliente);
        Assertions.assertNotNull(cliente.getId());
    }


}
