package br.com.ucsal.service;

import lombok.RequiredArgsConstructor;
import br.com.ucsal.domain.Escola;
import br.com.ucsal.domain.Instituicao;
import br.com.ucsal.repository.EscolaRepository;
import br.com.ucsal.repository.InstituicaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EscolaService {
    private final EscolaRepository escolaRepository;
    private final InstituicaoRepository instituicaoRepository;

    public Escola cadastrarEscola(Escola escola) {
        Instituicao ies = instituicaoRepository.findById(escola.getInstituicaoVinculada().getId())
                .orElseThrow(() -> new RuntimeException("Instituição não econtrada"));
        escola.setInstituicaoVinculada(ies);

        if(escola.getStatus() == null)
            escola.setStatus(true);
        return escolaRepository.save(escola);
    }

    public Escola buscarPorId(Long id) {
        return escolaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Escola não encontrada."));
    }

    public List<Escola> buscarTodas() {
        return escolaRepository.findAll();
    }

    public Escola atualizarEscola(Long id, Escola dados) {
        Escola escola = buscarPorId(id);
        escola.setNome(dados.getNome());
        escola.setCoordenador(dados.getCoordenador());

        if (dados.getInstituicaoVinculada() != null && dados.getInstituicaoVinculada().getId() != null) {
            Instituicao ies = instituicaoRepository.findById(dados.getInstituicaoVinculada().getId())
                    .orElseThrow(() -> new RuntimeException("Instituição não encontrada."));
            escola.setInstituicaoVinculada(ies);
        }
        return escolaRepository.save(escola);
    }

    public Escola inativarEscola(Long id) {
        Escola escola = buscarPorId(id);
        escola.setStatus(false);
        return escolaRepository.save(escola);
    }
}
