package br.com.ucsal.controller;

import br.com.ucsal.domain.Escola;
import br.com.ucsal.service.EscolaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/escolas")
@RequiredArgsConstructor
public class EscolaController {
    private final EscolaService escolaService;

    @PostMapping
    public ResponseEntity<Escola> cadastrarEscola(@RequestBody Escola escola) {
        return ResponseEntity.status(HttpStatus.CREATED).body(escolaService.cadastrarEscola(escola));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Escola> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(escolaService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Escola>> buscarTodos() {
        return ResponseEntity.ok(escolaService.buscarTodas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Escola> atualizarEscola(@RequestBody Escola escola, @PathVariable Long id) {
        return ResponseEntity.ok(escolaService.atualizarEscola(id, escola));
    }

    @PutMapping("/{id}/inativar")
    public ResponseEntity<Escola> inativarEscola(@PathVariable Long id) {
        return ResponseEntity.ok(escolaService.inativarEscola(id));
    }
}
