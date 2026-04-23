//modificação : novo controller para o profissional obter o histórico completo de um paciente — profissional item 4.3 do cenário TED
package main.java.br.com.ucsal.controller;

import lombok.RequiredArgsConstructor;
import main.java.br.com.ucsal.service.ProntuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/prontuarios")
@RequiredArgsConstructor
public class ProntuarioController {

    private final ProntuarioService prontuarioService;

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<Map<String, Object>> buscarHistoricoPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(prontuarioService.buscarHistoricoPaciente(pacienteId));
    }
}
//fim modificação
