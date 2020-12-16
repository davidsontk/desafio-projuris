package com.desafio.projuris.repository;

import com.desafio.projuris.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServicoRepository extends JpaRepository <OrdemServico, Integer> {

}
