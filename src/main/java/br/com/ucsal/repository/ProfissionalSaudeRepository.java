package br.com.ucsal.repository;

import br.com.ucsal.domain.ProfissionalSaude;
import br.com.ucsal.domain.Requisicao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfissionalSaudeRepository extends JpaRepository<ProfissionalSaude, Long> {
    List<Requisicao> findByProfissionalId(Long profissionalId);

    List<Requisicao> findByMedicamentoId(Long medicamentoId);
}
