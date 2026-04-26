package br.com.ucsal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "ies")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Instituicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 50, unique = true)
    private String nome;

    @OneToMany(mappedBy = "instituicaoVinculada")
    @JsonIgnore
    private List<Unidade> unidades;

    @OneToMany(mappedBy = "instituicaoVinculada")
    @JsonIgnore
    private List<Escola> escolas;
}
