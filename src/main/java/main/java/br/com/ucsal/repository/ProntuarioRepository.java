package main.java.br.com.ucsal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import main.java.br.com.ucsal.domain.Prontuario;


public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {

    //modificação : busca prontuário pelo ID do paciente, necessário para retornar histórico (item 4.3)
    Optional<Prontuario> findByPacienteId(Long pacienteId);
    //fim modificação
}
