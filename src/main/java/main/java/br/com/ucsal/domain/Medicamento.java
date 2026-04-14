package main.java.br.com.ucsal.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "medicamentos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String nome;

    @NotBlank
    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String fornecedor;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_armazenamento", nullable = false, length = 30)
    private FormaArmazenamento formaArmazenamento;

    @NotNull
    @Column(name = "quantidade_estoque", nullable = false)
    private Integer quantidadeEstoque;

    @NotNull
    @Column(name="data_validade", nullable = false)
    private LocalDate dataValidade;

    @NotNull
    @Column(name="data_aquisicao", nullable = false)
    private LocalDate dataAquisicao;

    @NotNull
    @Column(nullable = false)
    private Boolean ativo = true;
}