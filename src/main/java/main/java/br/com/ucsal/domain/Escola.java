package main.java.br.com.ucsal.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "escolas")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Escola {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String nome;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String coordenador;

    @NotNull
    @Column(nullable = false)
    private Boolean status = true;

    @ManyToOne
    @JoinColumn(name = "ies_id", nullable = false)
    private Instituicao instituicaoVinculada;
}
