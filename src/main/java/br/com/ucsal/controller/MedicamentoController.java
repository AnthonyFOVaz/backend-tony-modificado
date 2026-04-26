package br.com.ucsal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import br.com.ucsal.domain.Medicamento;
import br.com.ucsal.dto.MedicamentoResponse;
import br.com.ucsal.service.MedicamentoService;

@RestController
@RequestMapping("/api/medicamentos")
@RequiredArgsConstructor
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    @PostMapping
    public ResponseEntity<MedicamentoResponse> cadastrar(@RequestBody @Valid Medicamento medicamento) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MedicamentoResponse.from(medicamentoService.cadastrarMedicamento(medicamento)));
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoResponse>> listar() {
        return ResponseEntity.ok(medicamentoService.buscarTodos().stream()
                .map(MedicamentoResponse::from).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(MedicamentoResponse.from(medicamentoService.buscarPorId(id)));
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<MedicamentoResponse> inativar(@PathVariable Long id) {
        return ResponseEntity.ok(MedicamentoResponse.from(medicamentoService.inativarMedicamento(id)));
    }

    @PatchMapping("/{id}/reativar")
    public ResponseEntity<MedicamentoResponse> reativar(@PathVariable Long id) {
        return ResponseEntity.ok(MedicamentoResponse.from(medicamentoService.reativarMedicamento(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoResponse> atualizarEstoque(@PathVariable Long id,
                                                                 @RequestParam Integer quantidade) {
        return ResponseEntity.ok(MedicamentoResponse.from(medicamentoService.atualizarEstoque(id, quantidade)));
    }

    @PatchMapping("/{id}/baixar-estoque")
    public ResponseEntity<MedicamentoResponse> baixarEstoque(@PathVariable Long id,
                                                              @RequestParam Integer quantidade) {
        return ResponseEntity.ok(MedicamentoResponse.from(medicamentoService.baixarEstoque(id, quantidade)));
    }
}
