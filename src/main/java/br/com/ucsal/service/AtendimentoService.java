package br.com.ucsal.service;

import br.com.ucsal.domain.Atendimento;
import br.com.ucsal.domain.ProfissionalSaude;
import br.com.ucsal.domain.Prontuario;
import br.com.ucsal.repository.AtendimentoRepository;
import br.com.ucsal.repository.ProfissionalSaudeRepository;
import br.com.ucsal.repository.ProntuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AtendimentoService {
    private final AtendimentoRepository atendimentoRepository;
    private final ProntuarioRepository prontuarioRepository;
    private final ProfissionalSaudeRepository profissionalSaudeRepository;

    public Atendimento cadastrarAtendimento(Atendimento atendimento) {
        Prontuario prontuario = prontuarioRepository.findById(atendimento.getProntuario().getId())
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado."));
        ProfissionalSaude profissional = profissionalSaudeRepository.findById(atendimento.getProfissional().getId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado."));

        atendimento.setProntuario(prontuario);
        atendimento.setProfissional(profissional);

        if (atendimento.getDataHoraInicio() == null)
            atendimento.setDataHoraInicio(LocalDateTime.now());
        return atendimentoRepository.save(atendimento);
    }

    public Atendimento buscarPorId(Long id) {
        return atendimentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atendimento não encontrado."));
    }

    public List<Atendimento> buscarTodos() {
        return atendimentoRepository.findAll();
    }

    public Atendimento encerrarAtendimento(Long id) {
        Atendimento atendimento = buscarPorId(id);
        atendimento.setDataHoraEncerramento(LocalDateTime.now());
        return atendimentoRepository.save(atendimento);
    }

    public Atendimento atualizarAtendimento(Long id, Atendimento dados) {
        Atendimento existente = buscarPorId(id);

        if (dados.getProfissional() != null && dados.getProfissional().getId() != null) {
            ProfissionalSaude profissional = profissionalSaudeRepository.findById(dados.getProfissional().getId())
                    .orElseThrow(() -> new RuntimeException("Profissional não encontrado."));
            existente.setProfissional(profissional);
        }
        if (dados.getTipoAtendimento() != null) existente.setTipoAtendimento(dados.getTipoAtendimento());
        if (dados.getDataHoraInicio() != null) existente.setDataHoraInicio(dados.getDataHoraInicio());
        existente.setSintomas(dados.getSintomas());
        existente.setDiagnostico(dados.getDiagnostico());
        existente.setMedicacaoDosagem(dados.getMedicacaoDosagem());
        existente.setTratamento(dados.getTratamento());
        existente.setDataHoraEncerramento(dados.getDataHoraEncerramento());

        return atendimentoRepository.save(existente);
    }

    public List<Atendimento> listarPorProntuario(Long prontuarioId) {
        return atendimentoRepository.findAllByProntuarioId(prontuarioId);
    }

    public List<Atendimento> listarPorProfissional(Long profissionalId) {
        return atendimentoRepository.findAllByProfissionalId(profissionalId);
    }
}