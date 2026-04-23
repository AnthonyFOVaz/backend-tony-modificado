package br.com.ucsal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

// corpo da requisição de login — validado antes de chegar no AuthController
@Getter @Setter
public class LoginRequest {

    @Email
    @NotBlank
    private String email;   // usado como identificador único do usuário (subject do JWT)

    @NotBlank
    private String senha;   // comparada com o hash BCrypt armazenado no banco
}
