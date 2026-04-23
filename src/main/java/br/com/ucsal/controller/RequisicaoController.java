package br.com.ucsal.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import br.com.ucsal.domain.Requisicao;
import br.com.ucsal.service.RequisicaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requisicoes")
@RequiredArgsConstructor
public class RequisicaoController {

    private final RequisicaoService requisicaoService;

    @PostMapping
    public ResponseEntity<Requisicao> cadastrar(@RequestBody @Valid Requisicao requisicao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(requisicaoService.criarRequisicao(requisicao));
    }

    @GetMapping
    public ResponseEntity<List<Requisicao>> listar() {
        return ResponseEntity.ok(requisicaoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Requisicao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(requisicaoService.buscarPorId(id));
    }

    @GetMapping("/profissional/{profissionalId}")
    public ResponseEntity<List<Requisicao>> listarPorProfissional(@PathVariable Long profissionalId) {
        return ResponseEntity.ok(requisicaoService.buscarPorProfissional(profissionalId));
    }

    @GetMapping("/medicamento/{medicamentoId}")
    public ResponseEntity<List<Requisicao>> listarPorMedicamento(@PathVariable Long medicamentoId) {
        return ResponseEntity.ok(requisicaoService.buscarPorMedicamento(medicamentoId));
    }
}
