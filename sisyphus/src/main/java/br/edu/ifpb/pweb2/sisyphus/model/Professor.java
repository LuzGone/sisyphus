package br.edu.ifpb.pweb2.sisyphus.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message="Campo obrigatório!")
    protected String nome;
    
    @NotBlank(message="Campo obrigatório!")
    protected String fone;

    @NotBlank(message="Campo obrigatório!")
    @Pattern(regexp= "[0-9]{6}", message="Matrícula deve conter exatamente 6 números!")
    protected String matricula;

    @ManyToOne
    @JoinColumn(name = "curso")
    protected Curso curso;
    
    @NotBlank(message="Campo obrigatório!")
    protected String login;

    @Size(min=3, max=42 ,message="A senha deverá ter pelo menos 3 caracteres e no máximo 42")
    protected String senha;

    @OneToMany(mappedBy = "relator")
    protected List<Processo> listaDeProcessos;

    @ManyToMany(mappedBy = "membros")
    protected List<Colegiado> listaColegiados;


    public Professor(int id, String nome, String fone, String matricula, String login, String senha){
        this.id = id;
        this.nome = nome;
        this.fone = fone;
        this.matricula = matricula;
        this.login = login;
        this.senha = senha;
    }

    public void adicionarProcesso(Processo processo){
        this.listaDeProcessos.add(processo);
    }

    public void adicionarColegiado(Colegiado colegiado){
        this.listaColegiados.add(colegiado);
    }

    @Override
    public String toString(){
        return this.nome;
    }

}
