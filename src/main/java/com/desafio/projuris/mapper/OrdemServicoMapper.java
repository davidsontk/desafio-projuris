package com.desafio.projuris.mapper;

import com.desafio.projuris.dto.OrdemServicoDTO;
import com.desafio.projuris.model.Cliente;
import com.desafio.projuris.model.OrdemServico;
import org.springframework.stereotype.Component;

@Component
public class OrdemServicoMapper {

    public OrdemServico converterParaOrdemServico(OrdemServicoDTO ordemServicoDTO, Cliente cliente) {
        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setMarca(ordemServicoDTO.getMarca());
        ordemServico.setDescricao(ordemServicoDTO.getDescricao());
        ordemServico.setTipo(ordemServicoDTO.getTipo());
        ordemServico.setCliente(cliente);

        return ordemServico;
    }
}
