package br.com.ucsal.domain;

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

    @Column(name = "quantidade_solicitada", nullable = false)
    private Integer quantidadeSolicitada = 1;

    @Column(nullable = false)
    private Boolean atendida = false;

    @NotNull
    @Column(name="data_requisicao", nullable = false)
    private LocalDate dataRequisicao;

    @Column(name = "data_atendimento")
    private LocalDate dataAtendimento;

    @PrePersist
    public void prePersist() {
        if (quantidadeSolicitada == null || quantidadeSolicitada < 1) {
            quantidadeSolicitada = 1;
        }

        if (atendida == null) {
            atendida = false;
        }
    }
}
