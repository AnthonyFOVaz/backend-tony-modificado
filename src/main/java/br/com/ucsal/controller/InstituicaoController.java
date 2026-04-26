package br.com.ucsal.controller;

import br.com.ucsal.domain.Instituicao;
import br.com.ucsal.dto.InstituicaoResponse;
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
    public ResponseEntity<InstituicaoResponse> cadastrar(@RequestBody Instituicao instituicao) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(InstituicaoResponse.from(instituicaoService.cadastrarIes(instituicao)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstituicaoResponse> buscarPorID(@PathVariable Long id) {
        return ResponseEntity.ok(InstituicaoResponse.from(instituicaoService.buscarPorId(id)));
    }

    @GetMapping
    public ResponseEntity<List<InstituicaoResponse>> listar() {
        return ResponseEntity.ok(instituicaoService.buscarTodas().stream()
                .map(InstituicaoResponse::from).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstituicaoResponse> atualizarInstituicao(@RequestBody Instituicao instituicao,
                                                                     @PathVariable Long id) {
        return ResponseEntity.ok(InstituicaoResponse.from(instituicaoService.atualizarIes(id, instituicao)));
    }
}
