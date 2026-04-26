package br.com.ucsal.dto;

import br.com.ucsal.domain.CategoriaPaciente;
import br.com.ucsal.domain.Paciente;

import java.time.LocalDate;

public record PacienteResponse(
        Long id,
        String nome,
        CategoriaPaciente categoria,
        String celular,
        String email,
        LocalDate dataCadastro,
        Boolean ativo,
        EscolaResponse escola,
        UnidadeResponse unidade) {

    public static PacienteResponse from(Paciente p) {
        return new PacienteResponse(
                p.getId(),
                p.getNome(),
                p.getCategoria(),
                p.getCelular(),
                p.getEmail(),
                p.getDataCadastro(),
                p.getAtivo(),
                p.getEscola() != null ? EscolaResponse.from(p.getEscola()) : null,
                p.getUnidade() != null ? UnidadeResponse.from(p.getUnidade()) : null);
    }
}
