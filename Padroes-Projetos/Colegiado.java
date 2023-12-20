import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Colegiado {

    private Date dataDoInicio;
    private Date dataDoFim;
    private String descricao;
    private Curso curso;
    private Coordenador coordenador;
    private List<Professor> membros =  new ArrayList<Professor>();
    private List<Processo> processos = new ArrayList<Processo>();
    private List<Reuniao> reuniaos = new ArrayList<Reuniao>();

    public Colegiado(Date dataDoInicio, Date dataDoFim, Curso curso, Coordenador coordenador) {
        this.dataDoInicio = dataDoInicio;
        this.dataDoFim = dataDoFim;
        this.descricao = "Colegiado de " + curso;
        this.curso = curso;
        this.coordenador = coordenador;
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

    public Curso getCurso() {
        return curso;
    }


}
