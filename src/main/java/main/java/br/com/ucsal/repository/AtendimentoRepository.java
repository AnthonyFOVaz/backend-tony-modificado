package main.java.br.com.ucsal.repository;

import main.java.br.com.ucsal.domain.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    List<Atendimento> findByProntuarioId(Long prontuarioId);

    List<Atendimento> findByProfissionalId(Long profissionalId);
}
