package com.desafio.projuris.mapper;

import com.desafio.projuris.dto.OrdemServicoPendenciaDTO;
import com.desafio.projuris.model.OrdemServico;
import com.desafio.projuris.model.OrdemServicoPendencia;
import org.springframework.stereotype.Component;

@Component
public class OrdemServicoPendenciaMapper {

    public OrdemServicoPendencia convertarParaOrdemServico(OrdemServicoPendenciaDTO ordemServicoPendenciaDTO, OrdemServico ordemServico) {
        OrdemServicoPendencia ordemServicoPendencia = new OrdemServicoPendencia();
        ordemServicoPendencia.setDescricao(ordemServicoPendenciaDTO.getDescricao());
        ordemServicoPendencia.setOrdemServicoId(ordemServico);
        return ordemServicoPendencia;
    }
}
