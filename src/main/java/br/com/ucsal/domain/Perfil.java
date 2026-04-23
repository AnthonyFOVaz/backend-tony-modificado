package br.com.ucsal.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="perfis")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String nome;

    //modificação : @JsonIgnore impede que a lista de usuários seja retornada nas respostas da API, evitando recursão infinita
    @JsonIgnore
    //fim modificação
    @ManyToMany(mappedBy = "perfis") 
    private List<Usuario> usuarios;
}
