package br.com.ucsal.dto;

import br.com.ucsal.domain.Perfil;

public record PerfilResponse(Long id, String nome) {

    public static PerfilResponse from(Perfil p) {
        return new PerfilResponse(p.getId(), p.getNome());
    }
}
