package br.com.ucsal.controller;

import lombok.RequiredArgsConstructor;
import br.com.ucsal.domain.ProfissionalSaude;
import br.com.ucsal.dto.ProfissionalSaudeResponse;
import br.com.ucsal.service.ProfissionalSaudeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profissionais")
@RequiredArgsConstructor
public class ProfissionalSaudeController {

    private final ProfissionalSaudeService profissionalSaudeService;

    @PostMapping
    public ResponseEntity<ProfissionalSaudeResponse> cadastrar(@RequestBody ProfissionalSaude profissional) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ProfissionalSaudeResponse.from(profissionalSaudeService.cadastrarProfissional(profissional)));
    }

    @GetMapping
    public ResponseEntity<List<ProfissionalSaudeResponse>> listar() {
        return ResponseEntity.ok(profissionalSaudeService.buscarTodos().stream()
                .map(ProfissionalSaudeResponse::from).toList());
    }

    @GetMapping("/me")
    public ResponseEntity<ProfissionalSaudeResponse> buscarMeuCadastro(Authentication authentication) {
        return ResponseEntity.ok(ProfissionalSaudeResponse.from(
                profissionalSaudeService.buscarPorEmailUsuario(authentication.getName())));
    }

    @PutMapping("/me")
    public ResponseEntity<?> atualizarMeuCadastro(Authentication authentication,
                                                  @RequestBody ProfissionalSaude profissional) {
        try {
            return ResponseEntity.ok(ProfissionalSaudeResponse.from(
                    profissionalSaudeService.atualizarMeuCadastro(authentication.getName(), profissional)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalSaudeResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ProfissionalSaudeResponse.from(profissionalSaudeService.buscarPorId(id)));
    }

    @PutMapping("/{id}/inativar")
    public ResponseEntity<ProfissionalSaudeResponse> inativar(@PathVariable Long id) {
        return ResponseEntity.ok(ProfissionalSaudeResponse.from(profissionalSaudeService.inativarProfissional(id)));
    }

    @PutMapping("/{id}/reativar")
    public ResponseEntity<ProfissionalSaudeResponse> reativar(@PathVariable Long id) {
        return ResponseEntity.ok(ProfissionalSaudeResponse.from(profissionalSaudeService.reativarProfissional(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfissionalSaudeResponse> atualizar(@PathVariable Long id,
                                                               @RequestBody ProfissionalSaude profissional) {
        return ResponseEntity.ok(ProfissionalSaudeResponse.from(profissionalSaudeService.atualizarProfissional(id, profissional)));
    }
}
