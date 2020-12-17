package com.desafio.projuris.repository;

import com.desafio.projuris.model.OrdemServico;
import com.desafio.projuris.model.OrdemServicoPendencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdemServicoPendenciaRepository extends JpaRepository <OrdemServicoPendencia, Integer> {

    List<OrdemServicoPendencia> findAllByOrdemServicoIdAndDataEncerramentoIsNull(OrdemServico ordemServico);

    List<OrdemServicoPendencia> findAllByOrdemServicoId(OrdemServico ordemServico);
}


