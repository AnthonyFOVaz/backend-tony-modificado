package br.com.ucsal.dto;

import br.com.ucsal.domain.Escola;

public record EscolaResponse(
        Long id,
        String nome,
        String coordenador,
        Boolean status,
        InstituicaoResponse instituicaoVinculada) {

    public static EscolaResponse from(Escola e) {
        return new EscolaResponse(
                e.getId(),
                e.getNome(),
                e.getCoordenador(),
                e.getStatus(),
                e.getInstituicaoVinculada() != null
                        ? InstituicaoResponse.from(e.getInstituicaoVinculada())
                        : null);
    }
}
