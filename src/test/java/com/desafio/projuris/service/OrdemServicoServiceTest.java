package com.desafio.projuris.service;

import com.desafio.projuris.dto.ClienteDTO;
import com.desafio.projuris.dto.OrdemServicoDTO;
import com.desafio.projuris.dto.RegistroAtendimentoDTO;
import com.desafio.projuris.enums.StatusOrdemEnum;
import com.desafio.projuris.handler.exceptions.ExcecaoNegocio;
import com.desafio.projuris.mapper.OrdemServicoMapper;
import com.desafio.projuris.mapper.OrdemServicoPendenciaMapper;
import com.desafio.projuris.model.Cliente;
import com.desafio.projuris.model.OrdemServico;
import com.desafio.projuris.model.StatusOrdem;
import com.desafio.projuris.repository.OrdemServicoPendenciaRepository;
import com.desafio.projuris.repository.OrdemServicoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.desafio.projuris.enums.StatusOrdemEnum.EM_ATENDIMENTO;
import static com.desafio.projuris.enums.StatusOrdemEnum.FECHADO;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class OrdemServicoServiceTest {

    @InjectMocks
    private OrdemServicoService ordemServicoService;

    @Mock
    private ClienteService clienteService;

    @Mock
    private OrdemServicoRepository ordemServicoRepository;

    @Mock
    private OrdemServicoMapper ordemServicoMapper;

    @Mock
    private OrdemServicoPendenciaMapper ordemServicoPendenciaMapper;

    @Mock
    private OrdemServicoPendenciaRepository ordemServicoPendenciaRepository;


    @Test
    void deveRetornarExcecaoQuandoOrdemServicoEstaNull() {
        Assertions.assertThrows(ExcecaoNegocio.class, () -> {
            ordemServicoService.registrarOrdemServico(null);
        });
    }

    @Test
    void deveRetornarUmaOrdemDeServico() {
        OrdemServicoDTO ordemServicoDTO = mock(OrdemServicoDTO.class);
        OrdemServico ordemServico = mock(OrdemServico.class);
        ClienteDTO clienteDTO = mock(ClienteDTO.class);
        when(ordemServicoDTO.getCliente()).thenReturn(clienteDTO);
        Cliente cliente = mock(Cliente.class);
        when(clienteService.converterParaCliente(clienteDTO)).thenReturn(cliente);
        when(clienteService.registrarCliente(cliente)).thenReturn(cliente);
        when(ordemServicoMapper.converterParaOrdemServico(ordemServicoDTO, cliente)).thenReturn(ordemServico);
        when(ordemServicoRepository.save(ordemServico)).thenReturn(ordemServico);
        OrdemServico ordemServicoEsperado = ordemServicoService.registrarOrdemServico(ordemServicoDTO);

        Assertions.assertNotNull(ordemServicoEsperado);
        Assertions.assertEquals(ordemServico, ordemServicoEsperado);
    }

    @Test
    void deveAlterarOrdemServicoERetornarInicioEStatusAlterado() {
        RegistroAtendimentoDTO registroAtendimentoDTO = mock(RegistroAtendimentoDTO.class);
        OrdemServico ordemServico = retornarOrdemServico(EM_ATENDIMENTO.getStatusOrdem());
        when(ordemServicoRepository.findById(registroAtendimentoDTO.getIdOrdemServico())).thenReturn(Optional.of(ordemServico));
        when(ordemServicoRepository.save(ordemServico)).thenReturn(ordemServico);
        OrdemServico ordemServicoEsperado = ordemServicoService.registrarInicioAtendimento(registroAtendimentoDTO);

        Assertions.assertEquals(ordemServicoEsperado.getResponsavel(), ordemServico.getResponsavel());
        Assertions.assertEquals(ordemServicoEsperado.getInicioAtendimento(), ordemServico.getInicioAtendimento());
        Assertions.assertEquals(ordemServicoEsperado.getStatus(), EM_ATENDIMENTO.getStatusOrdem());
    }

    @Test
    void deveRetornarErroEmRegistrarInicioAtendimentoOrdemServico() {
        Assertions.assertThrows(ExcecaoNegocio.class, () -> {
            ordemServicoService.registrarInicioAtendimento(null);
        });
    }

    @Test
    void deveAlterarOrdemServicoERetornarFinalEStatusAlterado() {
        RegistroAtendimentoDTO registroAtendimentoDTO = mock(RegistroAtendimentoDTO.class);
        OrdemServico ordemServico = retornarOrdemServico(FECHADO.getStatusOrdem());
        when(ordemServicoRepository.findById(registroAtendimentoDTO.getIdOrdemServico())).thenReturn(Optional.of(ordemServico));
        when(ordemServicoRepository.save(ordemServico)).thenReturn(ordemServico);
        OrdemServico ordemServicoEsperado = ordemServicoService.registarFinalAtendimento(registroAtendimentoDTO);

        Assertions.assertEquals(ordemServicoEsperado.getResponsavel(), ordemServico.getResponsavel());
        Assertions.assertEquals(ordemServicoEsperado.getStatus(), FECHADO.getStatusOrdem());
        Assertions.assertEquals(ordemServicoEsperado.getDescricaoResolucao(), ordemServico.getDescricaoResolucao());
    }

    @Test
    void deveRetornarErroEmRegistrarFinalAtendimentoOrdemServico() {
        Assertions.assertThrows(ExcecaoNegocio.class, () -> {
            ordemServicoService.registarFinalAtendimento(null);
        });
    }


    private OrdemServico retornarOrdemServico(StatusOrdem statusOrdem) {
        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setResponsavel("Moises");
        ordemServico.setStatus(statusOrdem);
        ordemServico.setDescricaoResolucao("Realizado geral do computador");

        return ordemServico;
    }



}
