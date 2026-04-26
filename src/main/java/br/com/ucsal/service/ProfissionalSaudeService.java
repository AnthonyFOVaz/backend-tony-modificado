package br.com.ucsal.service;

import br.com.ucsal.domain.ProfissionalSaude;
import br.com.ucsal.domain.Usuario;
import br.com.ucsal.repository.ProfissionalSaudeRepository;
import br.com.ucsal.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProfissionalSaudeService {
    private final ProfissionalSaudeRepository profissionalSaudeRepository;
    private final UsuarioRepository usuarioRepository;

    public ProfissionalSaude cadastrarProfissional(ProfissionalSaude profissional) {
        Usuario usuario = usuarioRepository.findById(profissional.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        profissional.setUsuario(usuario);
        profissional.atualizarDiasTurnosAtendimentoPelaDisponibilidade();
        if (profissional.getDataDeCadastro() == null)
            profissional.setDataDeCadastro(LocalDate.now());
        return profissionalSaudeRepository.save(profissional);
    }

    public ProfissionalSaude buscarPorId(Long id) {
        return profissionalSaudeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado."));
    }

    public ProfissionalSaude buscarPorEmailUsuario(String email) {
        return profissionalSaudeRepository.findByUsuarioEmail(email)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado para o usuário logado."));
    }

    public List<ProfissionalSaude> buscarTodos() {
        return profissionalSaudeRepository.findAll();
    }

    public ProfissionalSaude inativarProfissional(Long id) {
        ProfissionalSaude profissional = buscarPorId(id);
        profissional.setAtivo(false);
        return profissionalSaudeRepository.save(profissional);
    }

    public ProfissionalSaude reativarProfissional(Long id) {
        ProfissionalSaude profissional = buscarPorId(id);
        profissional.setAtivo(true);
        return profissionalSaudeRepository.save(profissional);
    }

    public ProfissionalSaude atualizarProfissional(Long id, ProfissionalSaude dados) {
        ProfissionalSaude profissional = buscarPorId(id);
        profissional.setNome(dados.getNome());
        profissional.setEspecialidade(dados.getEspecialidade());
        profissional.setNumeroRegistro(dados.getNumeroRegistro());
        profissional.setConselhoRegional(dados.getConselhoRegional());
        profissional.setDiasTurnosAtendimento(dados.getDiasTurnosAtendimento());
        profissional.setDataHoraDisponibilidade(dados.getDataHoraDisponibilidade());
        profissional.atualizarDiasTurnosAtendimentoPelaDisponibilidade();
        return profissionalSaudeRepository.save(profissional);
    }

    public ProfissionalSaude atualizarMeuCadastro(String email, ProfissionalSaude dados) {
        ProfissionalSaude profissional = buscarPorEmailUsuario(email);
        validarDadosComplementares(dados);
        validarDisponibilidade(dados);

        profissional.setEspecialidade(dados.getEspecialidade());
        profissional.setNumeroRegistro(dados.getNumeroRegistro());
        profissional.setConselhoRegional(dados.getConselhoRegional());
        profissional.setDataHoraDisponibilidade(dados.getDataHoraDisponibilidade());
        profissional.atualizarDiasTurnosAtendimentoPelaDisponibilidade();

        return profissionalSaudeRepository.save(profissional);
    }

    private void validarDadosComplementares(ProfissionalSaude dados) {
        if (estaEmBranco(dados.getEspecialidade())) {
            throw new IllegalArgumentException("Especialidade é obrigatória.");
        }

        if (estaEmBranco(dados.getConselhoRegional())) {
            throw new IllegalArgumentException("Conselho regional é obrigatório.");
        }

        if (estaEmBranco(dados.getNumeroRegistro())) {
            throw new IllegalArgumentException("Número de registro é obrigatório.");
        }
    }

    private void validarDisponibilidade(ProfissionalSaude dados) {
        if (dados.getDataHoraDisponibilidade() == null || dados.getDataHoraDisponibilidade().isEmpty()) {
            throw new IllegalArgumentException("Informe pelo menos um dia e horário de atendimento.");
        }

        Set<DayOfWeek> dias = new HashSet<>();

        dados.getDataHoraDisponibilidade().forEach(disponibilidade -> {
            if (disponibilidade.getDiaSemana() == null) {
                throw new IllegalArgumentException("Dia de atendimento é obrigatório.");
            }

            if (!dias.add(disponibilidade.getDiaSemana())) {
                throw new IllegalArgumentException("Cada dia pode ter apenas um período de atendimento.");
            }

            if (estaEmBranco(disponibilidade.getHorarioInicio()) || estaEmBranco(disponibilidade.getHorarioFim())) {
                throw new IllegalArgumentException("Horário de entrada e saída são obrigatórios.");
            }

            try {
                LocalTime inicio = LocalTime.parse(disponibilidade.getHorarioInicio());
                LocalTime fim = LocalTime.parse(disponibilidade.getHorarioFim());

                if (!fim.isAfter(inicio)) {
                    throw new IllegalArgumentException("Horário de saída precisa ser maior que o horário de entrada.");
                }
            } catch (DateTimeException e) {
                throw new IllegalArgumentException("Horários devem estar no formato HH:mm.");
            }
        });
    }

    private boolean estaEmBranco(String valor) {
        return valor == null || valor.isBlank();
    }
}
