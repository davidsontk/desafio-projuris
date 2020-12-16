package com.desafio.projuris.repository;

import com.desafio.projuris.model.OrdemServicoPendencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServicoPendenciaRepository extends JpaRepository <OrdemServicoPendencia, Integer> {
}


