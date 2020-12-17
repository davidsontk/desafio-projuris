package com.desafio.projuris.repository;

import com.desafio.projuris.model.EnderecoCliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class EnderecoClienteRepositoryTest {

    @Autowired
    private EnderecoClienteRepository enderecoClienteRepository;

    @Test
    public void deveSalvarEnderecoCliente() {
        EnderecoCliente enderecoCliente = new EnderecoCliente();
        enderecoCliente.setRua("Rua teste");
        enderecoCliente.setNumero(10);
        enderecoCliente = enderecoClienteRepository.save(enderecoCliente);

        Assertions.assertNotNull(enderecoCliente);
        Assertions.assertNotNull(enderecoCliente.getId());
    }

}
