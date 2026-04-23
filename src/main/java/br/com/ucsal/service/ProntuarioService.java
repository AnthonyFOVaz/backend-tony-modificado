package br.com.ucsal.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import br.com.ucsal.domain.Atendimento;
import br.com.ucsal.domain.Prontuario;
import br.com.ucsal.repository.AtendimentoRepository;
import br.com.ucsal.repository.ProntuarioRepository;

@Service
@RequiredArgsConstructor
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;
    private final AtendimentoRepository atendimentoRepository;

    public Map<String, Object> buscarHistoricoPaciente(Long pacienteId) {
        Prontuario prontuario = prontuarioRepository.findByPacienteId(pacienteId)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado para o paciente."));

        List<Atendimento> atendimentos = atendimentoRepository.findByProntuarioId(prontuario.getId());

        Map<String, Object> historico = new LinkedHashMap<>();
        historico.put("prontuario", prontuario);
        historico.put("atendimentos", atendimentos);
        return historico;
    }
}
