package br.com.ucsal.domain;

//modificação : import do @JsonIgnore para ocultar a senha nas respostas da API
import com.fasterxml.jackson.annotation.JsonIgnore;
//fim modificação
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String nome;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    //modificação : @JsonIgnore impede que a senha seja retornada nas respostas da API
    @JsonIgnore
    //fim modificação
    @NotBlank
    @Column(nullable = false)
    private String senha;

    @NotNull
    @Column(nullable = false)
    private Boolean ativo = true;

    @ManyToMany
    @JoinTable(name = "usuarios_perfis",
                joinColumns = @JoinColumn(name = "usuario_id"),
                inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private List<Perfil> perfis;
}
