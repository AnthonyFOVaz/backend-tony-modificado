package br.com.ucsal.service;

import lombok.RequiredArgsConstructor;
import br.com.ucsal.domain.Atendimento;
import br.com.ucsal.domain.ProfissionalSaude;
import br.com.ucsal.domain.Prontuario;
import br.com.ucsal.repository.AtendimentoRepository;
import br.com.ucsal.repository.ProfissionalSaudeRepository;
import br.com.ucsal.repository.ProntuarioRepository;
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

    public List<Atendimento> listarPorProntuario(Long prontuarioId) {
        return atendimentoRepository.findByProntuarioId(prontuarioId);
    }

    public List<Atendimento> listarPorProfissional(Long profissionalId) {
        return atendimentoRepository.findByProfissionalId(profissionalId);
    }
}
