package br.com.ucsal.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import br.com.ucsal.domain.Medicamento;
import br.com.ucsal.domain.ProfissionalSaude;
import br.com.ucsal.domain.Requisicao;
import br.com.ucsal.repository.MedicamentoRepository;
import br.com.ucsal.repository.ProfissionalSaudeRepository;
import br.com.ucsal.repository.RequisicaoRepository;

@Service
@RequiredArgsConstructor
public class RequisicaoService {
    private final RequisicaoRepository requisicaoRepository;
    private final MedicamentoRepository medicamentoRepository;
    private final ProfissionalSaudeRepository profissionalSaudeRepository;

    public Requisicao criarRequisicao(Requisicao requisicao, String emailUsuarioLogado) {
        Medicamento medicamento = medicamentoRepository.findById(requisicao.getMedicamento().getId())
                .orElseThrow(() -> new RuntimeException("Medicamento não encontrado."));
        ProfissionalSaude profissional = profissionalSaudeRepository.findByUsuarioEmail(emailUsuarioLogado)
                .orElseThrow((() -> new RuntimeException("Profissional não encontrado para o usuario logado.")));

        if (requisicao.getCaraterRequisicao() == null) {
            throw new RuntimeException("Carater da requisicao é obrigatório.");
        }

        if (requisicao.getQuantidadeSolicitada() == null || requisicao.getQuantidadeSolicitada() < 1) {
            throw new RuntimeException("Quantidade solicitada deve ser maior que zero.");
        }

        requisicao.setMedicamento(medicamento);
        requisicao.setProfissionalSaude(profissional);
        requisicao.setAtendida(false);
        requisicao.setDataAtendimento(null);

        if (requisicao.getDataRequisicao() == null)
            requisicao.setDataRequisicao(LocalDate.now());
        return requisicaoRepository.save(requisicao);
    }

    @Transactional
    public Requisicao atenderRequisicao(Long id, Integer quantidadeAtendida) {
        Requisicao requisicao = buscarPorId(id);

        if (Boolean.TRUE.equals(requisicao.getAtendida())) {
            throw new RuntimeException("Requisição já atendida.");
        }

        Integer quantidade = quantidadeAtendida != null ? quantidadeAtendida : requisicao.getQuantidadeSolicitada();
        if (quantidade == null || quantidade < 1) {
            throw new RuntimeException("Quantidade atendida deve ser maior que zero.");
        }

        Medicamento medicamento = requisicao.getMedicamento();
        medicamento.setQuantidadeEstoque(medicamento.getQuantidadeEstoque() + quantidade);
        medicamentoRepository.save(medicamento);

        requisicao.setQuantidadeSolicitada(quantidade);
        requisicao.setAtendida(true);
        requisicao.setDataAtendimento(LocalDate.now());
        return requisicaoRepository.save(requisicao);
    }

    public Requisicao buscarPorId(Long id) {
        return requisicaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Requisição não encontrada"));
    }

    public List<Requisicao> buscarTodos() {
        return requisicaoRepository.findAll();
    }

    public Optional<Requisicao> buscarPorProfissional(Long profissionalId) {
        //modificação : corrigido para findByProfissionalSaudeId —
        return requisicaoRepository.findByProfissionalSaudeId(profissionalId);
        //fim modificação
    }

    public Optional<Requisicao> buscarPorMedicamento(Long medicamentoId) {
        //modificação : corrigido para findByMedicamentoId
        return requisicaoRepository.findByMedicamentoId(medicamentoId);
        //fim modificação
    }
}
