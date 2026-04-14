package main.java.br.com.ucsal.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="perfis")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String nome;

    @ManyToMany(mappedBy = "perfis")
    private List<Usuario> usuarios;
}
