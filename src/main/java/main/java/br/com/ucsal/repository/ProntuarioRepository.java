package main.java.br.com.ucsal.repository;

import main.java.br.com.ucsal.domain.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
}
