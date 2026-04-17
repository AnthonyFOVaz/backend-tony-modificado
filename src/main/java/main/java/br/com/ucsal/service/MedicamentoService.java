package main.java.br.com.ucsal.service;

import lombok.RequiredArgsConstructor;
import main.java.br.com.ucsal.domain.Medicamento;
import main.java.br.com.ucsal.repository.MedicamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicamentoService {
    private final MedicamentoRepository medicamentoRepository;

    public Medicamento cadastrarMedicamento(Medicamento medicamento) {
        if (medicamento.getDataAquisicao() == null)
            medicamento.setDataAquisicao(LocalDate.now());

        if (medicamento.getAtivo() == null)
            medicamento.setAtivo(true);

        return medicamentoRepository.save(medicamento);
    }

    public Medicamento buscarPorId(Long id) {
        return medicamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicamento nao encontrado."));
    }

    public List<Medicamento> buscarTodos() {
        return medicamentoRepository.findAll();
    }

    public Medicamento inativarMedicamento(Long id) {
        Medicamento medicamento = buscarPorId(id);
        medicamento.setAtivo(false);
        return medicamentoRepository.save(medicamento);
    }

    public Medicamento atualizarEstoque(Long id, Integer novaQuantidade) {
        Medicamento medicamento = buscarPorId(id);
        if (novaQuantidade < 0)
            throw new RuntimeException("Quantidade não pode ser negativa.");
        medicamento.setQuantidadeEstoque(novaQuantidade);
        return medicamentoRepository.save(medicamento);
    }

    public Medicamento baixarEstouque(Long id, Integer quantidade) {
        Medicamento medicamento = buscarPorId(id);
        if (quantidade < 0)
            throw new RuntimeException("Quantidade para baixa deve ser maior que 0.");
        if (medicamento.getQuantidadeEstoque() < quantidade)
            throw new RuntimeException("Estoque insuficiente.");
        medicamento.setQuantidadeEstoque(medicamento.getQuantidadeEstoque() - quantidade);
        return medicamentoRepository.save(medicamento);
    }
}
