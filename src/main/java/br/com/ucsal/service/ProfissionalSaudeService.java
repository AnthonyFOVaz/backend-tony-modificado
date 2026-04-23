package br.com.ucsal.service;

import lombok.RequiredArgsConstructor;
import br.com.ucsal.domain.ProfissionalSaude;
import br.com.ucsal.domain.Usuario;
import br.com.ucsal.repository.ProfissionalSaudeRepository;
import br.com.ucsal.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfissionalSaudeService {
    private final ProfissionalSaudeRepository profissionalSaudeRepository;
    private final UsuarioRepository usuarioRepository;

    public ProfissionalSaude cadastrarProfissional(ProfissionalSaude profissional) {
        Usuario usuario = usuarioRepository.findById(profissional.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        profissional.setUsuario(usuario);
        if (profissional.getDataDeCadastro() == null)
            profissional.setDataDeCadastro(LocalDate.now());
        return profissionalSaudeRepository.save(profissional);
    }

    public ProfissionalSaude buscarPorId(Long id) {
        return profissionalSaudeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profissional não econtrado."));
    }

    public List<ProfissionalSaude> buscarTodos() {
        return profissionalSaudeRepository.findAll();
    }

    public ProfissionalSaude inativarProfissional(Long id) {
        ProfissionalSaude profissional = buscarPorId(id);
        profissional.setAtivo(true);
        return profissionalSaudeRepository.save(profissional);
    }

    public ProfissionalSaude atualizarProfissional(Long id, ProfissionalSaude dados) {
        ProfissionalSaude profissional = buscarPorId(id);
        profissional.setNome(dados.getNome());
        profissional.setEspecialidade(dados.getEspecialidade());
        profissional.setNumeroRegistro(dados.getNumeroRegistro());
        profissional.setConselhoRegional(dados.getConselhoRegional());
        profissional.setDiasTurnosAtendimento(dados.getDiasTurnosAtendimento());

        return profissionalSaudeRepository.save(profissional);
    }
}
