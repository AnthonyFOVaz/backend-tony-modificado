package br.com.ucsal.service;

import lombok.RequiredArgsConstructor;
import br.com.ucsal.domain.CategoriaPaciente;
import br.com.ucsal.domain.Escola;
import br.com.ucsal.domain.Paciente;
import br.com.ucsal.domain.Prontuario;
import br.com.ucsal.domain.Unidade;
import br.com.ucsal.repository.EscolaRepository;
import br.com.ucsal.repository.PacienteRepository;
import br.com.ucsal.repository.ProntuarioRepository;
import br.com.ucsal.repository.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {
    private final PacienteRepository pacienteRepository;
    private final ProntuarioRepository prontuarioRepository;
    private final EscolaRepository escolaRepository;
    private final UnidadeRepository unidadeRepository;

    public Paciente cadastrarPaciente(Paciente paciente) {
        validarVinculoPaciente(paciente);
        paciente.setDataCadastro(LocalDate.now());
        paciente.setAtivo(true);

        Paciente pacienteSalvo = pacienteRepository.save(paciente);

        Prontuario prontuario = Prontuario.builder()
                .paciente(pacienteSalvo)
                .build();
        prontuarioRepository.save(prontuario);

        return pacienteSalvo;
    }

    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado."));
    }

    public Paciente inativarPaciente(Long id) {
        Paciente paciente = buscarPorId(id);
        paciente.setAtivo(false);
        return pacienteRepository.save(paciente);
    }

    public Paciente reativarPaciente(Long id) {
        Paciente paciente = buscarPorId(id);
        paciente.setAtivo(true);
        return pacienteRepository.save(paciente);
    }

    private void validarVinculoPaciente(Paciente paciente) {
        CategoriaPaciente categoria = paciente.getCategoria();
        if (categoria == null)
            throw new RuntimeException("Categoria do paciente é obrigatória.");

        switch (categoria) {
            case ALUNO, COLABORADOR_ESCOLA -> vincularEscola(paciente);
            case COLABORADOR_UNIDADE -> vincularUnidade(paciente);
            case EXTERNO -> {
                paciente.setEscola(null);
                paciente.setUnidade(null);
            }
        }
    }

    private void vincularEscola(Paciente paciente) {
        if (paciente.getEscola() == null || paciente.getEscola().getId() == null)
            throw new RuntimeException("Escola é obrigatória para a categoria informada.");

        Escola escola = escolaRepository.findById(paciente.getEscola().getId())
                .orElseThrow(() -> new RuntimeException("Escola não encontrada."));
        paciente.setEscola(escola);
        paciente.setUnidade(null);
    }

    private void vincularUnidade(Paciente paciente) {
        if (paciente.getUnidade() == null || paciente.getUnidade().getId() == null)
            throw new RuntimeException("Unidade é obrigatória para a categoria informada.");

        Unidade unidade = unidadeRepository.findById(paciente.getUnidade().getId())
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada."));
        paciente.setUnidade(unidade);
        paciente.setEscola(null);
    }
}
