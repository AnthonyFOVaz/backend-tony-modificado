package br.com.ucsal.dto;

import br.com.ucsal.domain.Instituicao;

public record InstituicaoResponse(Long id, String nome) {

    public static InstituicaoResponse from(Instituicao i) {
        return new InstituicaoResponse(i.getId(), i.getNome());
    }
}
