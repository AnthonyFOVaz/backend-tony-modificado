package br.com.ucsal.dto;

import br.com.ucsal.domain.Usuario;

import java.util.List;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        Boolean ativo,
        List<PerfilResponse> perfis) {

    public static UsuarioResponse from(Usuario u) {
        List<PerfilResponse> perfis = u.getPerfis() != null
                ? u.getPerfis().stream().map(PerfilResponse::from).toList()
                : List.of();
        return new UsuarioResponse(u.getId(), u.getNome(), u.getEmail(), u.getAtivo(), perfis);
    }
}
