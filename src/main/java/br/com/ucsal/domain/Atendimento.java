package br.com.ucsal.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
