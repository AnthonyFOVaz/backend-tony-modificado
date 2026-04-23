package br.com.ucsal.controller;

import br.com.ucsal.domain.Usuario;
import br.com.ucsal.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrarUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @GetMapping("/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.buscarPorEmail(email));
    }

    @PutMapping("/{id}/inativar")
    public ResponseEntity<Usuario> inativar(@RequestBody @Valid Long id) {
        return ResponseEntity.ok(usuarioService.inativarUsuario(id));
    }

    @PutMapping("/{id}/atualizar")
    public ResponseEntity<Usuario> atualizar(@RequestBody @Valid Long id, List<Long> perfisIds) {
        return ResponseEntity.ok(usuarioService.atualizarUsuario(id, perfisIds));
    }
}
