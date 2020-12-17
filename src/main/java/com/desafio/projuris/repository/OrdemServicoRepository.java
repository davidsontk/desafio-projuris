package com.desafio.projuris.repository;

import com.desafio.projuris.model.OrdemServico;
import com.desafio.projuris.model.StatusOrdem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdemServicoRepository extends JpaRepository <OrdemServico, Integer> {

    List<OrdemServico> findAllByStatusInAndResponsavelIgnoreCase(List<StatusOrdem> listaOrdens, String responsavel);

}
