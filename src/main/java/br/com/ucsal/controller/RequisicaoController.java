package br.com.ucsal.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import br.com.ucsal.domain.Requisicao;
import br.com.ucsal.dto.RequisicaoResponse;
import br.com.ucsal.service.RequisicaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/requisicoes")
@RequiredArgsConstructor
public class RequisicaoController {

    private final RequisicaoService requisicaoService;

    @PostMapping
    public ResponseEntity<RequisicaoResponse> cadastrar(@RequestBody @Valid Requisicao requisicao) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RequisicaoResponse.from(requisicaoService.criarRequisicao(requisicao)));
    }

    @GetMapping
    public ResponseEntity<List<RequisicaoResponse>> listar() {
        return ResponseEntity.ok(requisicaoService.buscarTodos().stream()
                .map(RequisicaoResponse::from).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequisicaoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(RequisicaoResponse.from(requisicaoService.buscarPorId(id)));
    }

    @GetMapping("/profissional/{profissionalId}")
    public ResponseEntity<Optional<RequisicaoResponse>> listarPorProfissional(@PathVariable Long profissionalId) {
        return ResponseEntity.ok(requisicaoService.buscarPorProfissional(profissionalId)
                .map(RequisicaoResponse::from));
    }

    @GetMapping("/medicamento/{medicamentoId}")
    public ResponseEntity<Optional<RequisicaoResponse>> listarPorMedicamento(@PathVariable Long medicamentoId) {
        return ResponseEntity.ok(requisicaoService.buscarPorMedicamento(medicamentoId)
                .map(RequisicaoResponse::from));
    }
}
