package br.com.ucsal.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataHoraDisponibilidade {
    @NotNull
    @JsonAlias("dia_semana")
    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana", nullable = false, length = 20)
    private DayOfWeek diaSemana;

    @NotBlank
    @JsonAlias("horario_inicio")
    @Column(name = "horario_inicio", nullable = false, length = 10)
    private String horarioInicio;

    @NotBlank
    @JsonAlias("horario_fim")
    @Column(name = "horario_fim", nullable = false, length = 10)
    private String horarioFim;
}
