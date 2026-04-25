package br.com.ucsal.config;

import br.com.ucsal.domain.Perfil;
import br.com.ucsal.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PerfilDataInitializer implements CommandLineRunner {
    private final PerfilRepository perfilRepository;

    @Override
    public void run(String... args) {
        criarPerfilSeNaoExistir("ADMIN");
        criarPerfilSeNaoExistir("PROFISSIONAL_SAUDE");
    }

    private void criarPerfilSeNaoExistir(String nome) {
        perfilRepository.findByNome(nome)
                .orElseGet(() -> perfilRepository.save(Perfil.builder().nome(nome).build()));
    }
}
