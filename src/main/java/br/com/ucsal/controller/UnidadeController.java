package br.com.ucsal.controller;

import br.com.ucsal.domain.Unidade;
import br.com.ucsal.dto.UnidadeResponse;
import br.com.ucsal.service.UnidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unidades")
@RequiredArgsConstructor
public class UnidadeController {
    private final UnidadeService unidadeService;

    @PostMapping
    public ResponseEntity<UnidadeResponse> cadastrarUnidade(@RequestBody Unidade unidade) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UnidadeResponse.from(unidadeService.cadastrarUnidade(unidade)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeResponse> buscarUnidade(@PathVariable Long id) {
        return ResponseEntity.ok(UnidadeResponse.from(unidadeService.buscarPorId(id)));
    }

    @GetMapping
    public ResponseEntity<List<UnidadeResponse>> listarUnidades() {
        return ResponseEntity.ok(unidadeService.buscarTodas().stream()
                .map(UnidadeResponse::from).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeResponse> atualizarUnidade(@PathVariable Long id, @RequestBody Unidade unidade) {
        return ResponseEntity.ok(UnidadeResponse.from(unidadeService.atualizarUnidade(id, unidade)));
    }

    @PutMapping("/{id}/inativar")
    public ResponseEntity<UnidadeResponse> inativarUnidade(@PathVariable Long id) {
        return ResponseEntity.ok(UnidadeResponse.from(unidadeService.inativarUnidade(id)));
    }

    @PutMapping("/{id}/reativar")
    public ResponseEntity<UnidadeResponse> reativarUnidade(@PathVariable Long id) {
        return ResponseEntity.ok(UnidadeResponse.from(unidadeService.reativarUnidade(id)));
    }
}
