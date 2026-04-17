package main.java.br.com.ucsal.service;

import lombok.RequiredArgsConstructor;
import main.java.br.com.ucsal.domain.Paciente;
import main.java.br.com.ucsal.domain.Prontuario;
import main.java.br.com.ucsal.repository.PacienteRepository;
import main.java.br.com.ucsal.repository.ProntuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
class PacienteService {
    private final PacienteRepository pacienteRepository;
    private final ProntuarioRepository prontuarioRepository;

    public Paciente cadastrarPaciente(Paciente paciente) {
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
}
