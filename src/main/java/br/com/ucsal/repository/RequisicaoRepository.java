package br.com.ucsal.repository;

import br.com.ucsal.domain.Requisicao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {

    //modificação : Spring Data JPA precisa do Id no final
    Optional<Requisicao> findByProfissionalSaudeId(Long profissionalId);
    //fim modificação

    //modificação : Spring Data JPA precisa do Id no final
    Optional<Requisicao> findByMedicamentoId(Long medicamentoId);
    //fim modificação
}
