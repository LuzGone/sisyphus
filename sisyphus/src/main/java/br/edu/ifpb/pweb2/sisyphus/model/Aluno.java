package br.edu.ifpb.pweb2.sisyphus.model;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private String nome;
    private String fone;
    private String matricula;
    private String login;
    private String senha;

    @OneToMany
    private ArrayList<Processo> listaProcessos;

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
