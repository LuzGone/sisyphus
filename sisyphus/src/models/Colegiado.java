package sisyphus.src.models;

import java.util.Date;
import java.util.List;


public class Colegiado {

    private Date dataDoInicio;
    private Date dataDoFim;
    private String descricao;
    private Curso curso;
    private Coordenador coordenador;
    private List<Professor> membros;
    private List<Processo> processos;
    private List<Reuniao> reuniaos;

    public Colegiado(Date dataDoInicio, Date dataDoFim, String descricao, Curso curso, Coordenador coordenador, List<Professor> membros, List<Processo> processos, List<Reuniao> reuniaos) {
        this.dataDoInicio = dataDoInicio;
        this.dataDoFim = dataDoFim;
        this.descricao = descricao;
        this.curso = curso;
        this.coordenador = coordenador;
        this.membros = membros;
        this.processos = processos;
        this.reuniaos = reuniaos;
    }

    public void adicionarMembro(Professor professor){
        this.membros.add(professor);
    }

    @Override
    public String toString(){
        return "Colegiado de " + this.curso;
    }

    public void adicionarReuniao(Reuniao reuniao){
        this.reuniaos.add(reuniao);
    }

    public void adicionarProcesso(Processo processo){
        this.processos.add(processo);
    }


}
