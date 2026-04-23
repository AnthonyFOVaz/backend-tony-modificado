package br.com.ucsal.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="atendimentos")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class Atendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="prontuario_id", nullable = false)
    private Prontuario prontuario;

    @NotNull
    @ManyToOne
    @JoinColumn(name="profissional_id", nullable = false)
    private ProfissionalSaude profissional;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="tipo_atendimento", nullable = false, length = 20)
    private TipoAtendimento tipoAtendimento;

    @NotNull
    @Column(name="data_hora_inicio", nullable = false)
    private LocalDateTime dataHoraInicio;

    @Column(columnDefinition = "TEXT")
    private String sintomas;

    @Column(columnDefinition = "TEXT")
    private String diagnostico;

    @Column(name = "medicacao_dosagem", columnDefinition = "TEXT")
    private String medicacaoDosagem;

    @Column(columnDefinition = "TEXT")
    private String tratamento;

    @Column(name = "data_hora_encerramento")
    private LocalDateTime dataHoraEncerramento;
}
