package com.desafio.projuris.repository;

import com.desafio.projuris.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Integer> {

    Optional<Cliente> findByEmailIgnoreCase(String email);
}
