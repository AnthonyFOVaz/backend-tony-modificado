//modificação : novo service criado para buscar histórico completo de um paciente (item 4.3)
package main.java.br.com.ucsal.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import main.java.br.com.ucsal.domain.Atendimento;
import main.java.br.com.ucsal.domain.Prontuario;
import main.java.br.com.ucsal.repository.AtendimentoRepository;
import main.java.br.com.ucsal.repository.ProntuarioRepository;

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
//fim modificação
