//modificação : novo controller para o profissional listar seus atendimentos registrados — profissional item 4.2 do cenário TED
package main.java.br.com.ucsal.controller;

import lombok.RequiredArgsConstructor;
import main.java.br.com.ucsal.domain.Atendimento;
import main.java.br.com.ucsal.service.AtendimentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atendimentos")
@RequiredArgsConstructor
public class AtendimentoController {

    private final AtendimentoService atendimentoService;

    @GetMapping("/profissional/{profissionalId}")
    public ResponseEntity<List<Atendimento>> listarPorProfissional(@PathVariable Long profissionalId) {
        return ResponseEntity.ok(atendimentoService.listarPorProfissional(profissionalId));
    }
}
//fim modificação
