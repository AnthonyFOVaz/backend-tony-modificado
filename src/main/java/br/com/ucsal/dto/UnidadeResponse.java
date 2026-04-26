package br.com.ucsal.dto;

import br.com.ucsal.domain.Unidade;

public record UnidadeResponse(
        Long id,
        String nome,
        String representante,
        Boolean ativo,
        InstituicaoResponse instituicaoVinculada) {

    public static UnidadeResponse from(Unidade u) {
        return new UnidadeResponse(
                u.getId(),
                u.getNome(),
                u.getRepresentante(),
                u.getAtivo(),
                u.getInstituicaoVinculada() != null
                        ? InstituicaoResponse.from(u.getInstituicaoVinculada())
                        : null);
    }
}
