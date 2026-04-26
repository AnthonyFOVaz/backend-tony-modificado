package br.com.ucsal.controller;

import br.com.ucsal.domain.Paciente;
import br.com.ucsal.dto.HistoricoPacienteResponse;
import br.com.ucsal.dto.PacienteResponse;
import br.com.ucsal.service.PacienteService;
import br.com.ucsal.service.ProntuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pacientes")
@RequiredArgsConstructor
public class PacienteController {
    private final PacienteService pacienteService;
    private final ProntuarioService prontuarioService;

    @PostMapping
    public ResponseEntity<PacienteResponse> cadastrar(@RequestBody Paciente paciente) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(PacienteResponse.from(pacienteService.cadastrarPaciente(paciente)));
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> listar() {
        return ResponseEntity.ok(pacienteService.buscarTodos().stream()
                .map(PacienteResponse::from).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(PacienteResponse.from(pacienteService.buscarPorId(id)));
    }

    @PutMapping("/{id}/inativar")
    public ResponseEntity<PacienteResponse> inativar(@PathVariable Long id) {
        return ResponseEntity.ok(PacienteResponse.from(pacienteService.inativarPaciente(id)));
    }

    @PutMapping("/{id}/reativar")
    public ResponseEntity<PacienteResponse> reativar(@PathVariable Long id) {
        return ResponseEntity.ok(PacienteResponse.from(pacienteService.reativarPaciente(id)));
    }

    @GetMapping("/{id}/historico")
    public ResponseEntity<HistoricoPacienteResponse> buscarHistorico(@PathVariable Long id) {
        return ResponseEntity.ok(prontuarioService.buscarHistoricoPaciente(id));
    }
}
