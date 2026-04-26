package br.com.ucsal.service;

import br.com.ucsal.domain.Atendimento;
import br.com.ucsal.domain.Paciente;
import br.com.ucsal.domain.Prontuario;
import br.com.ucsal.dto.HistoricoPacienteResponse;
import br.com.ucsal.repository.AtendimentoRepository;
import br.com.ucsal.repository.PacienteRepository;
import br.com.ucsal.repository.ProntuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;
    private final AtendimentoRepository atendimentoRepository;
    private final PacienteRepository pacienteRepository;

    public HistoricoPacienteResponse buscarHistoricoPaciente(Long pacienteId) {
        // doc 2.2: paciente nunca pode ficar sem prontuário — se não existir, cria agora
        Prontuario prontuario = prontuarioRepository.findByPacienteId(pacienteId)
                .orElseGet(() -> {
                    Paciente paciente = pacienteRepository.findById(pacienteId)
                            .orElseThrow(() -> new RuntimeException("Paciente não encontrado."));
                    return prontuarioRepository.save(
                            Prontuario.builder().paciente(paciente).build());
                });

        List<Atendimento> atendimentos = atendimentoRepository.findAllByProntuarioId(prontuario.getId());

        return HistoricoPacienteResponse.from(prontuario, atendimentos);
    }
}
