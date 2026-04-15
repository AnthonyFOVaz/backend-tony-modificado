package main.java.br.com.ucsal.repository;

import main.java.br.com.ucsal.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
