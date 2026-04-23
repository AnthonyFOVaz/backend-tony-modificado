package br.com.ucsal.repository;

import br.com.ucsal.domain.Escola;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EscolaRepository extends JpaRepository<Escola, Long> {
}
