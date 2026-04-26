package br.com.ucsal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ucsal.domain.Usuario;
import br.com.ucsal.dto.UsuarioResponse;
import br.com.ucsal.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody @Valid Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UsuarioResponse.from(usuarioService.cadastrarUsuario(usuario)));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar() {
        return ResponseEntity.ok(usuarioService.buscarTodos().stream()
                .map(UsuarioResponse::from).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(UsuarioResponse.from(usuarioService.buscarPorId(id)));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioResponse> buscarPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(UsuarioResponse.from(usuarioService.buscarPorEmail(email)));
    }

    @PutMapping("/{id}/inativar")
    public ResponseEntity<UsuarioResponse> inativar(@PathVariable Long id, Authentication authentication) {
        Usuario usuario = usuarioService.buscarPorId(id);

        if (authentication != null
                && authentication.getName() != null
                && authentication.getName().equalsIgnoreCase(usuario.getEmail())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(UsuarioResponse.from(usuarioService.inativarUsuario(id)));
    }

    @PutMapping("/{id}/reativar")
    public ResponseEntity<UsuarioResponse> reativar(@PathVariable Long id) {
        return ResponseEntity.ok(UsuarioResponse.from(usuarioService.reativarUsuario(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Long id,
                                                     @RequestBody List<Long> perfisIds) {
        return ResponseEntity.ok(UsuarioResponse.from(usuarioService.atualizarUsuario(id, perfisIds)));
    }
}
