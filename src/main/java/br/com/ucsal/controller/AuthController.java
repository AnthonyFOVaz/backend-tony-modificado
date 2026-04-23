package br.com.ucsal.controller;

import br.com.ucsal.dto.LoginRequest;
import br.com.ucsal.dto.LoginResponse;
import br.com.ucsal.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// endpoint de autenticação — único ponto público da API, librado pelo SecurityConfig em /auth/**
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    // gerencia a autenticação via Spring Security (valida email + senha contra o banco)
    private final AuthenticationManager authenticationManager;
    // gera o token JWT após autenticação bem-sucedida
    private final JwtUtil jwtUtil;

    // POST /auth/login — recebe email e senha, devolve o token JWT para uso nas próximas requisições
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
        );

        // auth.getName() retorna o email, que foi definido como subject do token em JwtUtil
        String token = jwtUtil.gerarToken(auth.getName());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
