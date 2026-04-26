package br.com.ucsal.controller;

import br.com.ucsal.domain.Escola;
import br.com.ucsal.dto.EscolaResponse;
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
    public ResponseEntity<EscolaResponse> cadastrarEscola(@RequestBody Escola escola) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(EscolaResponse.from(escolaService.cadastrarEscola(escola)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EscolaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(EscolaResponse.from(escolaService.buscarPorId(id)));
    }

    @GetMapping
    public ResponseEntity<List<EscolaResponse>> buscarTodos() {
        return ResponseEntity.ok(escolaService.buscarTodas().stream()
                .map(EscolaResponse::from).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EscolaResponse> atualizarEscola(@RequestBody Escola escola, @PathVariable Long id) {
        return ResponseEntity.ok(EscolaResponse.from(escolaService.atualizarEscola(id, escola)));
    }

    @PutMapping("/{id}/inativar")
    public ResponseEntity<EscolaResponse> inativarEscola(@PathVariable Long id) {
        return ResponseEntity.ok(EscolaResponse.from(escolaService.inativarEscola(id)));
    }

    @PutMapping("/{id}/reativar")
    public ResponseEntity<EscolaResponse> reativarEscola(@PathVariable Long id) {
        return ResponseEntity.ok(EscolaResponse.from(escolaService.reativarEscola(id)));
    }
}
