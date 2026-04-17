package main.java.br.com.ucsal.repository;

import main.java.br.com.ucsal.domain.Requisicao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {
    List<Requisicao> findByProfissionalSaude(Long profissionalId);

    List<Requisicao> findByMedicamento(Long medicamentId);
}
