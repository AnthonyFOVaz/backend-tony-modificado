package br.com.ucsal.controller;

import br.com.ucsal.domain.Atendimento;
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
    public ResponseEntity<Atendimento> cadastrar(@RequestBody @Valid Atendimento atendimento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(atendimentoService.cadastrarAtendimento(atendimento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atendimento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(atendimentoService.buscarPorId(id));
    }

    @PutMapping("/{id}/encerrar")
    public ResponseEntity<Atendimento> encerrar(@PathVariable Long id) {
        return ResponseEntity.ok(atendimentoService.encerrarAtendimento(id));
    }

    @GetMapping("/profissional/{profissionalId}")
    public ResponseEntity<List<Atendimento>> listarPorProfissional(@PathVariable Long profissionalId) {
        return ResponseEntity.ok(atendimentoService.listarPorProfissional(profissionalId));
    }

    @GetMapping("/prontuario/{prontuarioId}")
    public ResponseEntity<List<Atendimento>> listarPorProntuario(@PathVariable Long prontuarioId) {
        return ResponseEntity.ok(atendimentoService.listarPorProntuario(prontuarioId));
    }
}