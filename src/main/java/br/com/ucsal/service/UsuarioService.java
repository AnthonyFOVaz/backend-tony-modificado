package br.com.ucsal.service;

import lombok.RequiredArgsConstructor;
import br.com.ucsal.domain.Perfil;
import br.com.ucsal.domain.Usuario;
import br.com.ucsal.repository.PerfilRepository;
import br.com.ucsal.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;

    public Usuario cadastrarUsuario(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
            throw new RuntimeException("Email já cadastrado");
        if (usuario.getAtivo() == null)
            usuario.setAtivo(true);
        if (usuario.getPerfis() != null && usuario.getPerfis().isEmpty()) {
            List<Long> idsPerfis = usuario.getPerfis()
                    .stream()
                    .map(Perfil::getId) 
                    .toList();
            List<Perfil> perfis = perfilRepository.findAllById(idsPerfis);

            if (perfis.isEmpty())
                throw new RuntimeException("Nenhum perfil válido informado.");

            usuario.setPerfis(perfis);
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    public Usuario inativarUsuario(Long id) {
        Usuario usuario = buscarPorId(id);
        usuario.setAtivo(false);
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Long id, List<Long> perfisIds) {
        Usuario usuario = buscarPorId(id);
        List<Perfil> perfis = perfilRepository.findAllById(perfisIds);

        if (perfis.isEmpty())
            throw new RuntimeException("Nenhum perfil válido foi encontrado.");

        usuario.setPerfis(perfis);
        return usuarioRepository.save(usuario);
    }
}
