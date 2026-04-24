package br.com.ucsal.controller;

import br.com.ucsal.domain.Instituicao;
import br.com.ucsal.service.InstituicaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instituicoes")
@RequiredArgsConstructor
public class InstituicaoController {
    private final InstituicaoService instituicaoService;

    @PostMapping
    public ResponseEntity<Instituicao> cadastrar(@RequestBody Instituicao instituicao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(instituicaoService.cadastrarIes(instituicao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instituicao> buscarPorID(@PathVariable Long id) {
        return ResponseEntity.ok(instituicaoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Instituicao>> listar() {
        return ResponseEntity.ok(instituicaoService.buscarTodas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instituicao> atualizarInstituicao(@RequestBody Instituicao instituicao,
                                                 @PathVariable Long id) {
        return ResponseEntity.ok(instituicaoService.atualizarIes(id, instituicao));
    }
}