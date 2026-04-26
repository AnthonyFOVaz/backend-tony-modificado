package br.com.ucsal.controller;

import br.com.ucsal.domain.Atendimento;
import br.com.ucsal.dto.AtendimentoResponse;
import br.com.ucsal.service.AtendimentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atendimentos")
@RequiredArgsConstructor
public class AtendimentoController {

    private final AtendimentoService atendimentoService;

    @PostMapping
    public ResponseEntity<AtendimentoResponse> cadastrar(@RequestBody @Valid Atendimento atendimento) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AtendimentoResponse.from(atendimentoService.cadastrarAtendimento(atendimento)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtendimentoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(AtendimentoResponse.from(atendimentoService.buscarPorId(id)));
    }

    @PutMapping("/{id}/encerrar")
    public ResponseEntity<AtendimentoResponse> encerrar(@PathVariable Long id) {
        return ResponseEntity.ok(AtendimentoResponse.from(atendimentoService.encerrarAtendimento(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtendimentoResponse> atualizar(@PathVariable Long id, @RequestBody Atendimento dados) {
        return ResponseEntity.ok(AtendimentoResponse.from(atendimentoService.atualizarAtendimento(id, dados)));
    }

    @GetMapping("/profissional/{profissionalId}")
    public ResponseEntity<List<AtendimentoResponse>> listarPorProfissional(@PathVariable Long profissionalId) {
        return ResponseEntity.ok(atendimentoService.listarPorProfissional(profissionalId).stream()
                .map(AtendimentoResponse::from).toList());
    }

    @GetMapping("/prontuario/{prontuarioId}")
    public ResponseEntity<List<AtendimentoResponse>> listarPorProntuario(@PathVariable Long prontuarioId) {
        return ResponseEntity.ok(atendimentoService.listarPorProntuario(prontuarioId).stream()
                .map(AtendimentoResponse::from).toList());
    }
}
