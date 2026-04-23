package br.com.ucsal.repository;

import br.com.ucsal.domain.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    List<Atendimento> findAllByProntuarioId(Long prontuarioId);

    List<Atendimento> findAllByProfissionalId(Long profissionalId);
}