package br.com.ucsal.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="profissionais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfissionalSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(nullable = false, unique = true, length = 30)
    private String identificacaoProfissional;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String especialidade;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String conselhoRegional;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String numeroRegistro;

    @NotBlank
    @Column(nullable = false, length = 500)
    private String diasTurnosAtendimento;

    @JsonAlias("data_hora_disponibilidade")
    @ElementCollection
    @CollectionTable(
            name = "profissional_data_hora_disponibilidade",
            joinColumns = @JoinColumn(name = "profissional_id")
    )
    private List<DataHoraDisponibilidade> dataHoraDisponibilidade;

    @NotNull
    @Column(nullable = false)
    private LocalDate dataDeCadastro;

    @NotNull
    @Column(nullable = false)
    private Boolean ativo = true;

    @OneToOne
    @JoinColumn(name="usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    public void atualizarDiasTurnosAtendimentoPelaDisponibilidade() {
        if (dataHoraDisponibilidade == null || dataHoraDisponibilidade.isEmpty()) {
            return;
        }

        diasTurnosAtendimento = dataHoraDisponibilidade.stream()
                .map(disponibilidade -> disponibilidade.getDiaSemana()
                        + " "
                        + disponibilidade.getHorarioInicio()
                        + "-"
                        + disponibilidade.getHorarioFim())
                .collect(Collectors.joining("; "));
    }
}