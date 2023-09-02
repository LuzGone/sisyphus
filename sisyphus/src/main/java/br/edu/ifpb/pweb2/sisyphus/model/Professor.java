package br.edu.ifpb.pweb2.sisyphus.model;

public class Professor {
    private int id;
    private String nome;
    private String fone;
    private String matricula;
    private String login;
    private String senha;
    private boolean coordenador;


    public Professor(int id, String nome, String fone, String matricula, String login, String senha, boolean coordenador){
        this.id = id;
        this.nome = nome;
        this.fone = fone;
        this. matricula = matricula;
        this.login = login;
        this.senha = senha;
        this.coordenador = coordenador;
    }
    
}
