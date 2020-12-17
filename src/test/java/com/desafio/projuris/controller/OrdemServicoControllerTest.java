package com.desafio.projuris.controller;

import com.desafio.projuris.dto.OrdemServicoDTO;
import com.desafio.projuris.dto.OrdemServicoPendenciaDTO;
import com.desafio.projuris.dto.RegistroAtendimentoDTO;
import com.desafio.projuris.enums.ErroCodeEnum;
import com.desafio.projuris.handler.exceptions.ExcecaoNegocio;
import com.desafio.projuris.model.OrdemServico;
import com.desafio.projuris.model.OrdemServicoPendencia;
import com.desafio.projuris.service.OrdemServicoService;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class OrdemServicoControllerTest {

    @InjectMocks
    private OrdemServicoController ordemServicoController;

    @Mock
    private OrdemServicoService ordemServicoService;

    @Test
    public void deveRetornarRegistrarOrdemServico() {
        OrdemServicoDTO ordemServicoDTO = mock(OrdemServicoDTO.class);
        when(ordemServicoService.registrarOrdemServico(ordemServicoDTO)).thenReturn(new OrdemServico());
        ResponseEntity<OrdemServico> ordemServicoResponseEntity = ordemServicoController.registrarOrdemServico(ordemServicoDTO);

        Assertions.assertEquals(ordemServicoResponseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void deveRetornarRegistroInicioAtendimentoOrdemServico() {
        RegistroAtendimentoDTO registroAtendimentoDTO = mock(RegistroAtendimentoDTO.class);
        when(ordemServicoService.registrarInicioAtendimento(registroAtendimentoDTO)).thenReturn(new OrdemServico());
        ResponseEntity<OrdemServico> ordemServicoResponseEntity = ordemServicoController.registrarInicioAtendimentoOrdemServico(registroAtendimentoDTO);

        Assertions.assertEquals(ordemServicoResponseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void deveRetornarRegistroFinalAtendimentoOrdemServico() {
        RegistroAtendimentoDTO registroAtendimentoDTO = mock(RegistroAtendimentoDTO.class);
        when(ordemServicoService.registarFinalAtendimento(registroAtendimentoDTO)).thenReturn(new OrdemServico());
        ResponseEntity<OrdemServico> ordemServicoResponseEntity = ordemServicoController.registrarFinalAtendimentoOrdemServico(registroAtendimentoDTO);

        Assertions.assertEquals(ordemServicoResponseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void deveRetornarregistrarPendenciaOrdemServico() {
        OrdemServicoPendenciaDTO ordemServicoPendenciaDTO = mock(OrdemServicoPendenciaDTO.class);
        when(ordemServicoService.registrarInicioPendenciaOrdemServico(ordemServicoPendenciaDTO)).thenReturn(new OrdemServicoPendencia());
        ResponseEntity<OrdemServicoPendencia> ordemServicoResponseEntity = ordemServicoController.registrarPendenciaOrdemServico(ordemServicoPendenciaDTO);

        Assertions.assertEquals(ordemServicoResponseEntity.getStatusCode(), HttpStatus.CREATED);
    }


}
