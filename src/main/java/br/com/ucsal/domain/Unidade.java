package br.com.ucsal.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "unidades")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Unidade {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 50)
    private String representante;

    @NotNull
    @Column(nullable = false)
    private Boolean ativo = true;

    @ManyToOne
    @JoinColumn(nullable = false, name = "ies_id")
    private Instituicao instituicaoVinculada;
}
