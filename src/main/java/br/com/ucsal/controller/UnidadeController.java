package br.com.ucsal.controller;

import br.com.ucsal.domain.Unidade;
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
    public ResponseEntity<Unidade> cadastrarUnidade(@RequestBody Unidade unidade) {
        return ResponseEntity.status(HttpStatus.CREATED).body(unidadeService.cadastrarUnidade(unidade));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unidade> buscarUnidade(@PathVariable Long id) {
        return ResponseEntity.ok(unidadeService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Unidade>> listarUnidades() {
        return ResponseEntity.ok(unidadeService.buscarTodas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unidade> atualizarUnidade(@PathVariable Long id, @RequestBody Unidade unidade) {
        return ResponseEntity.ok(unidadeService.atualizarUnidade(id, unidade));
    }

    @PutMapping("/{id}/inativar")
    public ResponseEntity<Unidade> inativarUnidade(@PathVariable Long id) {
        return ResponseEntity.ok(unidadeService.inativarUnidade(id));
    }
}
