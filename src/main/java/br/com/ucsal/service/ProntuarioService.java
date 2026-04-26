package br.com.ucsal.service;

import br.com.ucsal.domain.Atendimento;
import br.com.ucsal.domain.Prontuario;
import br.com.ucsal.dto.HistoricoPacienteResponse;
import br.com.ucsal.repository.AtendimentoRepository;
import br.com.ucsal.repository.ProntuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;
    private final AtendimentoRepository atendimentoRepository;

    public HistoricoPacienteResponse buscarHistoricoPaciente(Long pacienteId) {
        Prontuario prontuario = prontuarioRepository.findByPacienteId(pacienteId)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado para o paciente."));

        List<Atendimento> atendimentos = atendimentoRepository.findAllByProntuarioId(prontuario.getId());

        return HistoricoPacienteResponse.from(prontuario, atendimentos);
    }
}
