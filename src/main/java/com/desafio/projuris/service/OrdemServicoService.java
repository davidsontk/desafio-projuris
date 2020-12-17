package com.desafio.projuris.service;

import com.desafio.projuris.dto.OrdemServicoDTO;
import com.desafio.projuris.dto.OrdemServicoPendenciaDTO;
import com.desafio.projuris.dto.RegistroAtendimentoDTO;
import com.desafio.projuris.dto.RespostaDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
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

    /**
     * Metodo registra uma ordem de servico, salvando a ordem de servico, cliente e endereco do cliente.
     * @param ordemServicoDTO
     * @return OrdemServico
     */
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

    /**
     * Metodo registra o inicio de atendimento, onde registra a data, o responsavel assume a ordem de servico e altera
     * o status da ordem para EM_ATENDIMENTO.
     * @param registroAtendimentoDTO
     * @return OrdemServico
     */
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

    /**
     * Metodo registra o final de um atendimento, setando resolucao na ordem e data final e mudando status para FINALIZADO.
     * @param registroAtendimentoDTO
     * @return OrdemServico
     */
    public OrdemServico registarFinalAtendimento(RegistroAtendimentoDTO registroAtendimentoDTO) {
        try {
            Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(registroAtendimentoDTO.getIdOrdemServico());
            if (ordemServico.isPresent()) {
                finalizarPendencias(ordemServico.get()); // finalizando pendencias, caso tenha alguma aberta
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

    /**
     * Metodo registra uma pendencia para ordem e altera o status da ordem para PENDENTE. Tambem salva a descricao da pendencia
     * e data de inicio
     * @param ordemServicoPendenciaDTO
     * @return OrdemServicoPendencia
     */
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

    /**
     * Metodo registra a finalizacao de uma pendencia, voltando a ordem para o status EM ATENDIMENTO. Caso nao tenha mais
     * nenhuma pendencia aberta. Tambem seta a data final da pendencia.
     * @param ordemServicoPendenciaDTO
     * @return ResponseEntity<Object>
     */
    public ResponseEntity<Object> registrarFinalizacaoPendenciaOrdemServico(OrdemServicoPendenciaDTO ordemServicoPendenciaDTO) {
        try {
            Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoPendenciaDTO.getIdOrdemServico());
            if (ordemServico.isPresent()) {
                Optional<OrdemServicoPendencia> ordemServicoPendencia = ordemServicoPendenciaRepository.findById(
                        ordemServicoPendenciaDTO.getIdOrdemServicoPendencia());
                if (ordemServicoPendencia.isPresent()) {
                    if (ordemServicoPendencia.get().getDataEncerramento() != null) {
                        return new ResponseEntity<>(new RespostaDTO("Pendência ID:" + ordemServicoPendencia.get().getId() +
                                " já se encontra fechada."), HttpStatus.OK);
                    }
                    ordemServicoPendencia.get().setDataEncerramento(new Date());
                    ordemServicoPendenciaRepository.save(ordemServicoPendencia.get());
                    if(verificarSeOrdemNaoPossuiPendenciasAbertas(ordemServico.get())) {
                        ordemServico.get().setStatus(StatusOrdemEnum.EM_ATENDIMENTO.getStatusOrdem());
                        ordemServicoRepository.save(ordemServico.get());
                    }
                    return new ResponseEntity<>(ordemServicoPendencia.get(), HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ExcecaoNegocio(ErroCodeEnum.ERRO_AO_SALVAR_PENDENCIA);
    }

    /**
     * Metodo lista as ordens de servico que estao com o status PENDENTE e EM ATENDIMENTO por responsavel.
     * @param responsavel
     * @return List<OrdemServico>
     */
    public List<OrdemServico> buscarOrdensServicoPorResponsavel(String responsavel) {
        try {
            return ordemServicoRepository.findAllByStatusInAndResponsavelIgnoreCase(Arrays.asList(StatusOrdemEnum.PENDENTE.getStatusOrdem(),
                    StatusOrdemEnum.EM_ATENDIMENTO.getStatusOrdem()),
                    responsavel);
        }catch (Exception e) {
            e.printStackTrace();
        }
        throw new ExcecaoNegocio(ErroCodeEnum.ERRO_AO_BUSCAR_ORDENS_POR_RESPONSAVEL);
    }


    /**
     * Metodo retorna todas as ordens de servico salvas no banco
     * @return List<OrdemServico>
     */
    public List<OrdemServico> buscarTodasOrdens() {
        try {
            return ordemServicoRepository.findAll();
        }catch (Exception e) {
            e.printStackTrace();
        }
        throw new ExcecaoNegocio(ErroCodeEnum.ERRO_AO_BUSCAR_ORDENS);
    }

    /**
     * Metodo lista todas pendencias de uma determinada Ordem de servico pelo id da ordem de servico.
     * @param ordemServicoId
     * @return List<OrdemServicoPendencia>
     */
    public List<OrdemServicoPendencia> buscarOrdensServicoPendenciaPorOrdemId(Integer ordemServicoId) {
        try {
            Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoId);
            if (ordemServico.isPresent()) {
                return ordemServicoPendenciaRepository.findAllByOrdemServicoId(ordemServico.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ExcecaoNegocio(ErroCodeEnum.ERRO_AO_BUSCAR_ORDENS_PENDENCIA);
    }

    /**
     * Metodo finaliza todas as pendencias de uma ordem de servico.
     * @param ordemServico
     */
    private void finalizarPendencias(OrdemServico ordemServico) {
        List<OrdemServicoPendencia> listaPendencias = ordemServicoPendenciaRepository.findAllByOrdemServicoIdAndDataEncerramentoIsNull(ordemServico);
        listaPendencias.forEach(pendencia -> {
            pendencia.setDataEncerramento(new Date());
            ordemServicoPendenciaRepository.save(pendencia);
        });
    }

    /**
     * Metodo verifica se a ordem de servico possui alguma pendencia aberta. Retornando um booleano como resposta.
     * @param ordemServico
     * @return boolean
     */
    private boolean verificarSeOrdemNaoPossuiPendenciasAbertas(OrdemServico ordemServico) {
        List<OrdemServicoPendencia> listaPendencias = ordemServicoPendenciaRepository.findAllByOrdemServicoIdAndDataEncerramentoIsNull(ordemServico);
        if (listaPendencias.isEmpty()){
            return true;
        }
        return false;
    }

}
