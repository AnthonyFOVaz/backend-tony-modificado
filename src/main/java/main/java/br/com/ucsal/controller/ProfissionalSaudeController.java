//modificação : novo controller para o profissional consultar seus próprios dados — profissional item 4.1 do cenário TED
package main.java.br.com.ucsal.controller;

import lombok.RequiredArgsConstructor;
import main.java.br.com.ucsal.domain.ProfissionalSaude;
import main.java.br.com.ucsal.service.ProfissionalSaudeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profissionais")
@RequiredArgsConstructor
public class ProfissionalSaudeController {

    private final ProfissionalSaudeService profissionalSaudeService;

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalSaude> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(profissionalSaudeService.buscarPorId(id));
    }
}
//fim modificação
