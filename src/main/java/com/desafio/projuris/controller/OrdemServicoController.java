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


@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @PostMapping
    public ResponseEntity<OrdemServico> registrarOrdemServico(@RequestBody OrdemServicoDTO ordemServico) {
        return new ResponseEntity<>(ordemServicoService.registrarOrdemServico(ordemServico), HttpStatus.CREATED);
    }

    @PutMapping("/registrar-inicio-atendimento")
    public ResponseEntity<OrdemServico> registrarInicioAtendimentoOrdemServico(@RequestBody RegistroAtendimentoDTO registroAtendimentoDTO) {
        return new ResponseEntity<>(ordemServicoService.registrarInicioAtendimento(registroAtendimentoDTO), HttpStatus.OK);
    }

    @PutMapping("/registrar-final-atendimento")
    public ResponseEntity<OrdemServico> registrarFinalAtendimentoOrdemServico(@RequestBody RegistroAtendimentoDTO registroAtendimentoDTO) {
        return new ResponseEntity<>(ordemServicoService.registarFinalAtendimento(registroAtendimentoDTO), HttpStatus.OK);
    }

    @PostMapping("/registrar-pendencia")
    public ResponseEntity<OrdemServicoPendencia> registrarPendenciaOrdemServico(@RequestBody OrdemServicoPendenciaDTO ordemServicoPendenciaDTO) {
        return new ResponseEntity<>(ordemServicoService.registrarInicioPendenciaOrdemServico(ordemServicoPendenciaDTO), HttpStatus.CREATED);
    }
}
