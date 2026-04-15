package main.java.br.com.ucsal.repository;

import main.java.br.com.ucsal.domain.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
}
