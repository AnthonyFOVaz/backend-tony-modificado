package main.java.br.com.ucsal.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="requisicoes")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class Requisicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="medicamento_id", nullable = false)
    private Medicamento medicamento;

    @NotNull
    @ManyToOne
    @JoinColumn(name="profissional_id", nullable = false)
    private ProfissionalSaude profissionalSaude;

    @Enumerated(EnumType.STRING)
    @Column(name="carater_solicitacao", nullable = false)
    private CaraterRequisicao caraterRequisicao;

    @NotNull
    @Column(name="data_requisicao", nullable = false)
    private LocalDate dataRequisicao;
}
