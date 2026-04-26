package br.com.ucsal.dto;

import br.com.ucsal.domain.Atendimento;
import br.com.ucsal.domain.Prontuario;

import java.util.List;

public record HistoricoPacienteResponse(
        ProntuarioResponse prontuario,
        List<AtendimentoResponse> atendimentos) {

    public static HistoricoPacienteResponse from(Prontuario prontuario, List<Atendimento> atendimentos) {
        return new HistoricoPacienteResponse(
                ProntuarioResponse.from(prontuario),
                atendimentos.stream().map(AtendimentoResponse::from).toList());
    }
}
