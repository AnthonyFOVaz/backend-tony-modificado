package br.com.ucsal.dto;

import br.com.ucsal.domain.CaraterRequisicao;
import br.com.ucsal.domain.Requisicao;

import java.time.LocalDate;

public record RequisicaoResponse(
        Long id,
        MedicamentoResponse medicamento,
        ProfissionalSaudeResponse profissionalSaude,
        CaraterRequisicao caraterRequisicao,
        Integer quantidadeSolicitada,
        Boolean atendida,
        LocalDate dataRequisicao,
        LocalDate dataAtendimento) {

    public static RequisicaoResponse from(Requisicao r) {
        return new RequisicaoResponse(
                r.getId(),
                r.getMedicamento() != null ? MedicamentoResponse.from(r.getMedicamento()) : null,
                r.getProfissionalSaude() != null ? ProfissionalSaudeResponse.from(r.getProfissionalSaude()) : null,
                r.getCaraterRequisicao(),
                r.getQuantidadeSolicitada(),
                r.getAtendida(),
                r.getDataRequisicao(),
                r.getDataAtendimento());
    }
}
