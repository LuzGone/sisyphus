package br.edu.ifpb.pweb2.sisyphus.model;

import java.util.Date;

public class Colegiado {
    private int id;
    private Date dataInicio;
    private Date dataFim;
    private String descricao;
    private String portaria;
    private String curso;

    public Colegiado() {

    }

    public Colegiado(Date dataInicio, Date dataFim, String descricao, String portaria, String curso) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.descricao = descricao;
        this.portaria = portaria;
        this.curso = curso;
    }

}
