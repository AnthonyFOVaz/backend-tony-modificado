package br.com.ucsal.dto;

import br.com.ucsal.domain.Prontuario;

public record ProntuarioResponse(Long id, PacienteResponse paciente) {

    public static ProntuarioResponse from(Prontuario p) {
        return new ProntuarioResponse(
                p.getId(),
                p.getPaciente() != null ? PacienteResponse.from(p.getPaciente()) : null);
    }
}
