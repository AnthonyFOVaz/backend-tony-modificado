package br.com.ucsal.controller;

import br.com.ucsal.service.ProntuarioService;
import lombok.RequiredArgsConstructor;
import br.com.ucsal.domain.Paciente;
import br.com.ucsal.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/pacientes")
@RequiredArgsConstructor
public class PacienteController {
    private final PacienteService pacienteService;
    private final ProntuarioService prontuarioService;


    @PostMapping
    public ResponseEntity<Paciente> cadastrar(@RequestBody Paciente paciente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.cadastrarPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listar() {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    @PutMapping("{id}/inativar")
    public ResponseEntity<Paciente> inativar(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.inativarPaciente(id));
    }

    @GetMapping("/{id}/historico")
    public ResponseEntity<Map<String, Object>> buscarHistorico(@PathVariable Long id) {
        return ResponseEntity.ok(prontuarioService.buscarHistoricoPaciente(id));
    }
}
