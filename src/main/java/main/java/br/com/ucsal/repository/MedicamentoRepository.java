package main.java.br.com.ucsal.repository;

import main.java.br.com.ucsal.domain.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
}
