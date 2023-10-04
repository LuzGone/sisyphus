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
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isCoordenador() {
        return coordenador;
    }

    public void setCoordenador(boolean coordenador) {
        this.coordenador = coordenador;
    }
}
