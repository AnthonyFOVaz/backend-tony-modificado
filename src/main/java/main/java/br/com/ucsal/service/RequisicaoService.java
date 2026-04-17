package main.java.br.com.ucsal.service;

import lombok.RequiredArgsConstructor;
import main.java.br.com.ucsal.domain.Medicamento;
import main.java.br.com.ucsal.domain.ProfissionalSaude;
import main.java.br.com.ucsal.domain.Requisicao;
import main.java.br.com.ucsal.repository.MedicamentoRepository;
import main.java.br.com.ucsal.repository.ProfissionalSaudeRepository;
import main.java.br.com.ucsal.repository.RequisicaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequisicaoService {
    private final RequisicaoRepository requisicaoRepository;
    private final MedicamentoRepository medicamentoRepository;
    private final ProfissionalSaudeRepository profissionalSaudeRepository;

    public Requisicao criarRequisicao(Requisicao requisicao) {
        Medicamento medicamento = medicamentoRepository.findById(requisicao.getMedicamento().getId())
                .orElseThrow(() -> new RuntimeException("Medicamento não encontrado."));
        ProfissionalSaude profissional = profissionalSaudeRepository.findById(requisicao.getProfissionalSaude().getId())
                .orElseThrow((() -> new RuntimeException("Profissional não encontrado.")));
        requisicao.setMedicamento(medicamento);
        requisicao.setProfissionalSaude(profissional);

        if (requisicao.getDataRequisicao() == null)
            requisicao.setDataRequisicao(LocalDate.now());
        return requisicaoRepository.save(requisicao);
    }

    public Requisicao buscarPorId(Long id) {
        return requisicaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Requisição não encontrada"));
    }

    public List<Requisicao> buscarTodos() {
        return requisicaoRepository.findAll();
    }

    public List<Requisicao> buscarPorProfissional(Long profissionalId) {
        return requisicaoRepository.findByProfissionalSaude(profissionalId);
    }

    public List<Requisicao> buscarPorMedicamento(Long medicamentoId) {
        return requisicaoRepository.findByMedicamento(medicamentoId);
    }
}
