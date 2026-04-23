package br.com.ucsal.service;

import br.com.ucsal.domain.Atendimento;
import br.com.ucsal.domain.Prontuario;
import br.com.ucsal.repository.AtendimentoRepository;
import br.com.ucsal.repository.ProntuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;
    private final AtendimentoRepository atendimentoRepository;

    public Map<String, Object> buscarHistoricoPaciente(Long pacienteId) {
        Prontuario prontuario = prontuarioRepository.findByPacienteId(pacienteId)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado para o paciente."));

        List<Atendimento> atendimentos = atendimentoRepository.findAllByProntuarioId(prontuario.getId());

        Map<String, Object> historico = new LinkedHashMap<>();
        historico.put("prontuario", prontuario);
        historico.put("atendimentos", atendimentos);
        return historico;
    }
}
