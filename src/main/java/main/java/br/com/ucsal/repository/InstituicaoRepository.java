package main.java.br.com.ucsal.repository;

import main.java.br.com.ucsal.domain.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
}
