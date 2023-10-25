package br.edu.ifpb.pweb2.sisyphus.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.Pattern;
// import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Data
@NoArgsConstructor
@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // @NotBlank(message="Campo obrigatório!")
    private String nome;
    
    private String fone;

    // @NotBlank(message="Campo obrigatório!")
    // @Pattern(regexp= "[0-9]{6}", message="Matrícula deve conter exatamente 6 números!")
    private String matricula;

    @NotBlank(message="Campo obrigatório!")
    private String login;

    // @NotBlank(message="Campo obrigatório!")
    // @Size(min=6,message="A senha deverá ter pelo menos 6 caracteres")
    private String senha;

    @OneToMany(mappedBy = "aluno")
    private List<Processo> listaProcessos;

    public Aluno(String nome, String fone, String matricula, String login, String senha) {
        this.nome = nome;
        this.fone = fone;
        this.matricula = matricula;
        this.login = login;
        this.senha = senha;
    }

    public void adicionarProcesso(Processo processo){
        this.listaProcessos.add(processo);
    }

}
