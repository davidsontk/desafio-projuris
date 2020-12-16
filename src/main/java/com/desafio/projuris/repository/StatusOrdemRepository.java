package com.desafio.projuris.repository;

import com.desafio.projuris.model.StatusOrdem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusOrdemRepository extends JpaRepository <StatusOrdem, Integer> {
}
