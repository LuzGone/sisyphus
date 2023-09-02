package br.edu.ifpb.pweb2.sisyphus.model;

public class Assunto {
    private int id;
    private String nome;

    public Assunto(String nome) {
        this.nome = nome;
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
}
