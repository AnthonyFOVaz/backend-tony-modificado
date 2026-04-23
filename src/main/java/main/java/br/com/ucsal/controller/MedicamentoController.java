//modificação : novo controller para gerenciamento de medicamentos — admin item 4 e profissional item 3.1 do cenário TED
package main.java.br.com.ucsal.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import main.java.br.com.ucsal.domain.Medicamento;
import main.java.br.com.ucsal.service.MedicamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
@RequiredArgsConstructor
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    @PostMapping
    public ResponseEntity<Medicamento> cadastrar(@RequestBody @Valid Medicamento medicamento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoService.cadastrarMedicamento(medicamento));
    }

    @GetMapping
    public ResponseEntity<List<Medicamento>> listar() {
        return ResponseEntity.ok(medicamentoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicamentoService.buscarPorId(id));
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Medicamento> inativar(@PathVariable Long id) {
        return ResponseEntity.ok(medicamentoService.inativarMedicamento(id));
    }

    @PutMapping("/{id}/estoque")
    public ResponseEntity<Medicamento> atualizarEstoque(@PathVariable Long id,
                                                        @RequestParam Integer quantidade) {
        return ResponseEntity.ok(medicamentoService.atualizarEstoque(id, quantidade));
    }

    @PatchMapping("/{id}/baixar-estoque")
    public ResponseEntity<Medicamento> baixarEstoque(@PathVariable Long id,
                                                     @RequestParam Integer quantidade) {
        return ResponseEntity.ok(medicamentoService.baixarEstouque(id, quantidade));
    }
}
//fim modificação
