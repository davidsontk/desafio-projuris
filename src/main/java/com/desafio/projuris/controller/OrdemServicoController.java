package com.desafio.projuris.controller;

import com.desafio.projuris.dto.OrdemServicoDTO;
import com.desafio.projuris.dto.OrdemServicoPendenciaDTO;
import com.desafio.projuris.dto.RegistroAtendimentoDTO;
import com.desafio.projuris.model.OrdemServico;
import com.desafio.projuris.model.OrdemServicoPendencia;
import com.desafio.projuris.service.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    /**
     * Endpoint registra a ordem de servico no sistema, deixando ela com o Status ABERTO. Tambem registra cliente
     * e o endereco, caso ele nao possua cadastro(email unique).
     *
     * Exemplo de dados JSON passado via endpoint:
     * {
     *      "tipo": "Desktop",
     *      "marca": "HP",
     *      "descricao": "Apresentou várias vezes tela azul durante o uso normal e depois não ligou mais",
     *      "cliente": {
     *          "nome": "Moises",
     *          "telefone": "6799632568",
     *          "email": "moises@gmail.com",
     *          "endereco": {
     *              "rua": "rua das bermudas",
     *              "numero":"43"
     *          }
     *      }
     * }
     *
     * @param ordemServico
     * @return ResponseEntity<OrdemServico>
     */
    @PostMapping
    public ResponseEntity<OrdemServico> registrarOrdemServico(@RequestBody OrdemServicoDTO ordemServico) {
        return new ResponseEntity<>(ordemServicoService.registrarOrdemServico(ordemServico), HttpStatus.CREATED);
    }

    /**
     * Endpoint registra o responsavel que ira fazer o atendimento na ordem de servico. Atribuindo data inicial, responsavel e
     * deixando ela com o status EM ATENDIMENTO.
     *
     * Exemplo de dados JSON passado via endpoint:
     * {
     * 	    "idOrdemServico": "3",
     *      "responsavel": "felipe"
     * }
     *
     * @param registroAtendimentoDTO
     * @return ResponseEntity<OrdemServico>
     */
    @PutMapping("/registrar-inicio-atendimento")
    public ResponseEntity<OrdemServico> registrarInicioAtendimentoOrdemServico(@RequestBody RegistroAtendimentoDTO registroAtendimentoDTO) {
        return new ResponseEntity<>(ordemServicoService.registrarInicioAtendimento(registroAtendimentoDTO), HttpStatus.OK);
    }

    /**
     * Endpoint registra o final do atendimento da ordem de servico, deixando ela com o status de FECHADO e
     * finaliza todas as pendencias abertas.
     *
     * Exemplo de dados JSON passado via endpoint:
     * {
     *      "idOrdemServico": "3",
     *      "responsavel": "felipe",
     *      "descricaoResolucao": "Foi visto que as memorias estavam com problema e foi realizado a troca delas."
     * }
     * @param registroAtendimentoDTO
     * @return ResponseEntity<OrdemServico>
     */
    @PutMapping("/registrar-final-atendimento")
    public ResponseEntity<OrdemServico> registrarFinalAtendimentoOrdemServico(@RequestBody RegistroAtendimentoDTO registroAtendimentoDTO) {
        return new ResponseEntity<>(ordemServicoService.registarFinalAtendimento(registroAtendimentoDTO), HttpStatus.OK);
    }

    /**
     * Endpoint registra uma pendencia da ordem, deixando ela com o status de PENDENTE. E criando um registro na tabela
     * ordem_servico_pendencia
     *
     * Exemplo de dados JSON passado via endpoint:
     * {
     * 	    "idOrdemServico": "3",
     *      "descricao": "Necessário trocar o HD"
     * }
     * @param ordemServicoPendenciaDTO
     * @return ResponseEntity<OrdemServicoPendencia>
     */
    @PostMapping("/registrar-pendencia")
    public ResponseEntity<OrdemServicoPendencia> registrarPendenciaOrdemServico(@RequestBody OrdemServicoPendenciaDTO ordemServicoPendenciaDTO) {
        return new ResponseEntity<>(ordemServicoService.registrarInicioPendenciaOrdemServico(ordemServicoPendenciaDTO), HttpStatus.CREATED);
    }

    /**
     * Endpoint registra a finalizacao de uma pendencia de ordem e volta ela para o status EM_ATENDIMENTO.
     *
     * Exemplo de dados JSON passado via endpoint:
     *{
     * 	    "idOrdemServico": "3",
     * 	    "idOrdemServicoPendencia": "6"
     * }
     * @param ordemServicoPendenciaDTO
     * @return ResponseEntity<Object>
     */
    @PutMapping("/registrar-final-pendencia")
    public ResponseEntity<Object> registrarPendenciaFinalizacaoOrdemServico(@RequestBody OrdemServicoPendenciaDTO ordemServicoPendenciaDTO) {
        return ordemServicoService.registrarFinalizacaoPendenciaOrdemServico(ordemServicoPendenciaDTO);
    }

    /**
     * Endpoint busca todas as ordens de servico com o status EM_ATENDIMENTO ou PENDENTE, vinculadas com o
     * responsavel pelo seu nome.
     *
     * @param responsavel
     * @return ResponseEntity<List<OrdemServico>>
     */
    @GetMapping("/buscar-ordens-por-responsavel/{responsavel}")
    public ResponseEntity<List<OrdemServico>> buscarTodasOrdensServicoPorResponsavel(@PathVariable String responsavel) {
        return new ResponseEntity<>(ordemServicoService.buscarOrdensServicoPorResponsavel(responsavel), HttpStatus.OK);
    }

    /**
     * Endpoint busca todas ordens salvas no sistema.
     *
     * @return ResponseEntity<List<OrdemServico>>
     */
    @GetMapping("/buscar-todas-ordens")
    public ResponseEntity<List<OrdemServico>> buscarTodasOrdens() {
        return new ResponseEntity<>(ordemServicoService.buscarTodasOrdens(), HttpStatus.OK);
    }

    /**
     * Endpoint busca todas pendencias vinculadas a uma ordem, pelo id da ordem de servico.
     *
     * @param idOrdem
     * @return ResponseEntity<List<OrdemServicoPendencia>>
     */
    @GetMapping("/buscar-pendencias-ordens/{idOrdem}")
    public ResponseEntity<List<OrdemServicoPendencia>> buscarOrdensServicoPendenciaPorOrdem(@PathVariable Integer idOrdem) {
        return new ResponseEntity<>(ordemServicoService.buscarOrdensServicoPendenciaPorOrdemId(idOrdem), HttpStatus.OK);
    }
}
