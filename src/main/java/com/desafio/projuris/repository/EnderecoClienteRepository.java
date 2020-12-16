package com.desafio.projuris.repository;

import com.desafio.projuris.model.EnderecoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoClienteRepository extends JpaRepository <EnderecoCliente, Integer> {

}
