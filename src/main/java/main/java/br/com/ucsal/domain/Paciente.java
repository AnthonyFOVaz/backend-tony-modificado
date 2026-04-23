package main.java.br.com.ucsal.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="pacientes")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaPaciente categoria;

    @NotBlank
    @Column(nullable = false, length = 20)
    private String celular;

    @Email
    @NotBlank
    @Column(nullable = false, length = 100)
    private String email;

    @NotNull
    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @NotNull
    @Column(nullable = false)
    private Boolean ativo = true;
}
