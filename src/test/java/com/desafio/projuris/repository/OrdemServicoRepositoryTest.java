package com.desafio.projuris.repository;


import com.desafio.projuris.model.Cliente;
import com.desafio.projuris.model.EnderecoCliente;
import com.desafio.projuris.model.OrdemServico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.desafio.projuris.enums.StatusOrdemEnum.ABERTO;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class OrdemServicoRepositoryTest {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoClienteRepository enderecoClienteRepository;

    @Test
    public void deveSalvarOrdemServico() {
        EnderecoCliente enderecoCliente = new EnderecoCliente();
        enderecoCliente.setRua("Rua feira");
        enderecoCliente.setNumero(10);
        enderecoCliente = enderecoClienteRepository.save(enderecoCliente);

        Cliente cliente = new Cliente();
        cliente.setEndereco(enderecoCliente);
        cliente.setNome("Larina");
        cliente.setEmail("larina@gmail.com");
        cliente = clienteRepository.save(cliente);

        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setStatus(ABERTO.getStatusOrdem());
        ordemServico.setMarca("Samsung");
        ordemServico.setDescricao("Caiu e trincou a lateral esquerda");
        ordemServico.setTipo("Notebook");
        ordemServico.setCliente(cliente);
        ordemServico = ordemServicoRepository.save(ordemServico);

        Assertions.assertNotNull(ordemServico);
        Assertions.assertNotNull(ordemServico.getId());
    }

}
