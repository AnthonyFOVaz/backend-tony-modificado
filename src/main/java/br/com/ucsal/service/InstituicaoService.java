package br.com.ucsal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ucsal.domain.Instituicao;
import br.com.ucsal.repository.InstituicaoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstituicaoService {
    private final InstituicaoRepository instituicaoRepository;

    public Instituicao cadastrarIes(Instituicao ies) {
        return instituicaoRepository.save(ies);
    }

    public Instituicao buscarPorId(Long id) {
        return instituicaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instituição não encontrada."));
    }

    public List<Instituicao> buscarTodas() {
        return instituicaoRepository.findAll();
    }

    public Instituicao atualizarIes(Long id, Instituicao dados) {
        Instituicao ies = buscarPorId(id);
        ies.setNome(dados.getNome());
        return instituicaoRepository.save(ies);
    }
}