package main.java.br.com.ucsal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import main.java.br.com.ucsal.domain.Requisicao;

public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {

    //modificação : Spring Data JPA precisa do Id no final
    List<Requisicao> findByProfissionalSaudeId(Long profissionalId);
    //fim modificação

    //modificação : Spring Data JPA precisa do Id no final
    List<Requisicao> findByMedicamentoId(Long medicamentoId);
    //fim modificação
}
