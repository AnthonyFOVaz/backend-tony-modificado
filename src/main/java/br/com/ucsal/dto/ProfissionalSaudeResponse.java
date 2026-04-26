package br.com.ucsal.dto;

import br.com.ucsal.domain.DataHoraDisponibilidade;
import br.com.ucsal.domain.ProfissionalSaude;

import java.time.LocalDate;
import java.util.List;

public record ProfissionalSaudeResponse(
        Long id,
        String identificacaoProfissional,
        String nome,
        String especialidade,
        String conselhoRegional,
        String numeroRegistro,
        String diasTurnosAtendimento,
        List<DataHoraDisponibilidade> dataHoraDisponibilidade,
        LocalDate dataDeCadastro,
        Boolean ativo,
        UsuarioResponse usuario) {

    public static ProfissionalSaudeResponse from(ProfissionalSaude p) {
        return new ProfissionalSaudeResponse(
                p.getId(),
                p.getIdentificacaoProfissional(),
                p.getNome(),
                p.getEspecialidade(),
                p.getConselhoRegional(),
                p.getNumeroRegistro(),
                p.getDiasTurnosAtendimento(),
                p.getDataHoraDisponibilidade(),
                p.getDataDeCadastro(),
                p.getAtivo(),
                p.getUsuario() != null ? UsuarioResponse.from(p.getUsuario()) : null);
    }
}
