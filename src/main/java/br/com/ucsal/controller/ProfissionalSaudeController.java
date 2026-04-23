package br.com.ucsal.controller;

import lombok.RequiredArgsConstructor;
import br.com.ucsal.domain.ProfissionalSaude;
import br.com.ucsal.service.ProfissionalSaudeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profissionais")
@RequiredArgsConstructor
public class ProfissionalSaudeController {

    private final ProfissionalSaudeService profissionalSaudeService;

    @PostMapping
    public ResponseEntity<ProfissionalSaude> cadastrar(@RequestBody ProfissionalSaude profissional) {
        return ResponseEntity.status(HttpStatus.CREATED).body(profissionalSaudeService.cadastrarProfissional(profissional));
    }

    @GetMapping
    public ResponseEntity<List<ProfissionalSaude>> listar() {
        return ResponseEntity.ok(profissionalSaudeService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalSaude> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(profissionalSaudeService.buscarPorId(id));
    }

    @PutMapping("{id}/inativar")
    public ResponseEntity<ProfissionalSaude> inativar(@PathVariable Long id) {
        return ResponseEntity.ok(profissionalSaudeService.inativarProfissional(id));
    }

    @PutMapping("{id}/atualizar")
    public ResponseEntity<ProfissionalSaude> atualizar(@PathVariable Long id
    , @RequestBody ProfissionalSaude profissional) {
        return ResponseEntity.ok(profissionalSaudeService.atualizarProfissional(id, profissional));
    }
}
