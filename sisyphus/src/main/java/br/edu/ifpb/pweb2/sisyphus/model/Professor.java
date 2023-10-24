package br.edu.ifpb.pweb2.sisyphus.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    protected String nome;
    protected String fone;
    protected String matricula;
    protected String login;
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
        return "Professor " + this.nome;
    }

}
