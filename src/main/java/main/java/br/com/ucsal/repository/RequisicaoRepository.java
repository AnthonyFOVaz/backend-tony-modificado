package main.java.br.com.ucsal.repository;

import main.java.br.com.ucsal.domain.Requisicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {
}
