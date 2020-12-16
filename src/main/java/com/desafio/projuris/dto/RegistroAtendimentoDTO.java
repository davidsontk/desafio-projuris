package com.desafio.projuris.dto;

import lombok.Data;

@Data
public class RegistroAtendimentoDTO {
    private Integer idOrdemServico;
    private String responsavel;
    private String descricaoResolucao;
}
