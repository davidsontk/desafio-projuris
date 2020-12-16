package com.desafio.projuris.service;

import com.desafio.projuris.dto.OrdemServicoDTO;
import com.desafio.projuris.dto.OrdemServicoPendenciaDTO;
import com.desafio.projuris.dto.RegistroAtendimentoDTO;
import com.desafio.projuris.enums.ErroCodeEnum;
import com.desafio.projuris.enums.StatusOrdemEnum;
import com.desafio.projuris.handler.exceptions.ExcecaoNegocio;
import com.desafio.projuris.mapper.OrdemServicoMapper;
import com.desafio.projuris.mapper.OrdemServicoPendenciaMapper;
import com.desafio.projuris.model.Cliente;
import com.desafio.projuris.model.OrdemServico;
import com.desafio.projuris.model.OrdemServicoPendencia;
import com.desafio.projuris.repository.OrdemServicoPendenciaRepository;
import com.desafio.projuris.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class OrdemServicoService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private OrdemServicoMapper ordemServicoMapper;

    @Autowired
    private OrdemServicoPendenciaMapper ordemServicoPendenciaMapper;

    @Autowired
    private OrdemServicoPendenciaRepository ordemServicoPendenciaRepository;

    public OrdemServico registrarOrdemServico(OrdemServicoDTO ordemServicoDTO) {
        try {
            Cliente cliente = clienteService.converterParaCliente(ordemServicoDTO.getCliente());
            OrdemServico ordemServico = ordemServicoMapper.converterParaOrdemServico(ordemServicoDTO, clienteService.registrarCliente(cliente));
            ordemServico.setStatus(StatusOrdemEnum.ABERTO.getStatusOrdem());
            return ordemServicoRepository.save(ordemServico);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ExcecaoNegocio(ErroCodeEnum.ERRO_AO_SALVAR_ORDEM_SERVICO);
    }

    public OrdemServico registrarInicioAtendimento(RegistroAtendimentoDTO registroAtendimentoDTO) {
        try {
            Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(registroAtendimentoDTO.getIdOrdemServico());
            if (ordemServico.isPresent()) {
                ordemServico.get().setResponsavel(registroAtendimentoDTO.getResponsavel());
                ordemServico.get().setInicioAtendimento(new Date());
                ordemServico.get().setStatus(StatusOrdemEnum.EM_ATENDIMENTO.getStatusOrdem());
                return ordemServicoRepository.save(ordemServico.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ExcecaoNegocio(ErroCodeEnum.ERRO_AO_SALVAR_INICIO_ATENDIMENTO);
    }

    public OrdemServico registarFinalAtendimento(RegistroAtendimentoDTO registroAtendimentoDTO) {
        try {
            Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(registroAtendimentoDTO.getIdOrdemServico());
            if (ordemServico.isPresent()) {
                ordemServico.get().setFinalAtendimento(new Date());
                ordemServico.get().setStatus(StatusOrdemEnum.FECHADO.getStatusOrdem());
                ordemServico.get().setDescricaoResolucao(registroAtendimentoDTO.getDescricaoResolucao());
                return ordemServicoRepository.save(ordemServico.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ExcecaoNegocio(ErroCodeEnum.ERRO_AO_SALVAR_FINAL_ATENDIMENTO);
    }

    public OrdemServicoPendencia registrarInicioPendenciaOrdemServico (OrdemServicoPendenciaDTO ordemServicoPendenciaDTO) {
        try {
            Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoPendenciaDTO.getIdOrdemServico());
            if (ordemServico.isPresent()) {
                ordemServico.get().setStatus(StatusOrdemEnum.PENDENTE.getStatusOrdem());
                OrdemServicoPendencia ordemServicoPendencia = ordemServicoPendenciaMapper.convertarParaOrdemServico(ordemServicoPendenciaDTO,
                        ordemServico.get());
                ordemServicoPendencia.setDataInicio(new Date());
                ordemServicoRepository.save(ordemServico.get());

                return ordemServicoPendenciaRepository.save(ordemServicoPendencia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ExcecaoNegocio(ErroCodeEnum.ERRO_AO_SALVAR_PENDENCIA);
    }

    /// AO FINALIZAR ORDEM DE SERVICO, VERIFICAR SE ELA ESTA NO STATUS EM_ATENDIMENTO E SE NAO POSSUI NENHUMA PENDENCIA

    /// AO CRIAR UMA PENDENCIA VERIFICAR SE ORDEM DE SERVICO ESTA NO STATUS EM_ATENDIMENTO

    /// AO FINALIZAR UMA PENDENCIA, ALTERAR O STATUS DA ORDEM PARA EM ATENDIMENTO CASO N TENHA MAIS NENHUMA PENDENCIA
}
