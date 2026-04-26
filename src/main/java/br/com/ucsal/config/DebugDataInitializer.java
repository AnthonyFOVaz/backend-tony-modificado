package br.com.ucsal.config;

import br.com.ucsal.domain.Perfil;
import br.com.ucsal.domain.Usuario;
import br.com.ucsal.repository.PerfilRepository;
import br.com.ucsal.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("debug")
@Order(2)
@RequiredArgsConstructor
public class DebugDataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (usuarioRepository.findByEmail("admin@ucsal.br").isPresent()) return;

        Perfil adminPerfil = perfilRepository.findByNome("ADMIN")
                .orElseThrow(() -> new RuntimeException("Perfil ADMIN não encontrado"));
        Perfil profPerfil = perfilRepository.findByNome("PROFISSIONAL_SAUDE")
                .orElseThrow(() -> new RuntimeException("Perfil PROFISSIONAL_SAUDE não encontrado"));

        Usuario admin = Usuario.builder()
                .nome("Admin Debug")
                .email("admin@ucsal.br")
                .senha(passwordEncoder.encode("admin123"))
                .ativo(true)
                .perfis(List.of(adminPerfil))
                .build();
        usuarioRepository.save(admin);

        Usuario prof = Usuario.builder()
                .nome("Profissional Debug")
                .email("prof@ucsal.br")
                .senha(passwordEncoder.encode("prof123"))
                .ativo(true)
                .perfis(List.of(profPerfil))
                .build();
        usuarioRepository.save(prof);

        System.out.println("[DEBUG] Usuários criados: admin@ucsal.br / admin123 | prof@ucsal.br / prof123");
    }
}
