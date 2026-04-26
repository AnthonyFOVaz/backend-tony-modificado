package br.com.ucsal.dto;

import br.com.ucsal.domain.FormaArmazenamento;
import br.com.ucsal.domain.Medicamento;

import java.time.LocalDate;

public record MedicamentoResponse(
        Long id,
        String nome,
        String descricao,
        String fornecedor,
        FormaArmazenamento formaArmazenamento,
        Integer quantidadeEstoque,
        LocalDate dataValidade,
        LocalDate dataAquisicao,
        Boolean ativo) {

    public static MedicamentoResponse from(Medicamento m) {
        return new MedicamentoResponse(
                m.getId(),
                m.getNome(),
                m.getDescricao(),
                m.getFornecedor(),
                m.getFormaArmazenamento(),
                m.getQuantidadeEstoque(),
                m.getDataValidade(),
                m.getDataAquisicao(),
                m.getAtivo());
    }
}
