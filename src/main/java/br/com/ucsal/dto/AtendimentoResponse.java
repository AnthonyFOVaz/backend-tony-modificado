package br.com.ucsal.dto;

import br.com.ucsal.domain.Atendimento;
import br.com.ucsal.domain.TipoAtendimento;

import java.time.LocalDateTime;

public record AtendimentoResponse(
        Long id,
        ProntuarioResponse prontuario,
        ProfissionalSaudeResponse profissional,
        TipoAtendimento tipoAtendimento,
        LocalDateTime dataHoraInicio,
        String sintomas,
        String diagnostico,
        String medicacaoDosagem,
        String tratamento,
        LocalDateTime dataHoraEncerramento) {

    public static AtendimentoResponse from(Atendimento a) {
        return new AtendimentoResponse(
                a.getId(),
                a.getProntuario() != null ? ProntuarioResponse.from(a.getProntuario()) : null,
                a.getProfissional() != null ? ProfissionalSaudeResponse.from(a.getProfissional()) : null,
                a.getTipoAtendimento(),
                a.getDataHoraInicio(),
                a.getSintomas(),
                a.getDiagnostico(),
                a.getMedicacaoDosagem(),
                a.getTratamento(),
                a.getDataHoraEncerramento());
    }
}
