package sisyphus.src.models;

import java.util.List;


public class Professor {

    protected String nome;
    
    protected String telefone;
    protected String matricula;
    protected Curso curso;
    protected String usuario;
    protected String senha;
    protected List<Processo> listaDeProcessos;
    protected List<Colegiado> listaColegiados;

    public Professor(String nome, String telefone, String matricula, Curso curso, String usuario, String senha, List<Processo> listaDeProcessos, List<Colegiado> listaColegiados) {
        this.nome = nome;
        this.telefone = telefone;
        this.matricula = matricula;
        this.curso = curso;
        this.usuario = usuario;
        this.senha = senha;
        this.listaDeProcessos = listaDeProcessos;
        this.listaColegiados = listaColegiados;
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
