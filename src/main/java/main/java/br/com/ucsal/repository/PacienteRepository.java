package main.java.br.com.ucsal.repository;

import main.java.br.com.ucsal.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
