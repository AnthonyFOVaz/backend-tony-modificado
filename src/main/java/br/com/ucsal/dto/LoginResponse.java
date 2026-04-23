package br.com.ucsal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

// resposta do login — o frontend deve enviar este token no header Authorization: Bearer <token>
@Getter
@AllArgsConstructor
public class LoginResponse {
    private String token;
}
