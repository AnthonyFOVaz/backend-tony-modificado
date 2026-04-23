package br.com.ucsal.service;

import lombok.RequiredArgsConstructor;
import br.com.ucsal.domain.Instituicao;
import br.com.ucsal.domain.Unidade;
import br.com.ucsal.repository.InstituicaoRepository;
import br.com.ucsal.repository.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnidadeService {
    private final UnidadeRepository unidadeRepository;
    private final InstituicaoRepository instituicaoRepository;

    public Unidade cadastrarUnidade(Unidade unidade) {
        Instituicao ies = instituicaoRepository.findById((unidade.getInstituicaoVinculada().getId()))
                .orElseThrow(() -> new RuntimeException("Instituicao não econtrada."));
        unidade.setInstituicaoVinculada(ies);

        if (unidade.getAtivo() == null)
            unidade.setAtivo(true);
        return unidadeRepository.save(unidade);
    }

    public Unidade buscarPorId(Long id) {
        return unidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada."));
    }

    public List<Unidade> buscarTodas() {
        return unidadeRepository.findAll();
    }

    public Unidade atualizarUnidade(Long id, Unidade dados) {
        Unidade unidade = buscarPorId(id);

        unidade.setNome(dados.getNome());
        unidade.setRepresentante(dados.getRepresentante());

        if (dados.getInstituicaoVinculada() != null && dados.getInstituicaoVinculada().getId() != null) {
            Instituicao ies = instituicaoRepository.findById(dados.getInstituicaoVinculada().getId())
                    .orElseThrow(() -> new RuntimeException("Instituição não econtrada."));
            unidade.setInstituicaoVinculada(ies);
        }

        return unidadeRepository.save(unidade);
    }

    public Unidade inativarUnidade(Long id) {
        Unidade unidade = buscarPorId(id);
        unidade.setAtivo(false);
        return unidadeRepository.save(unidade);
    }
}
