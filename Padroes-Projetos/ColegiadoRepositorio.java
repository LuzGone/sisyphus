import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class ColegiadoRepositorio{
    private static ColegiadoRepositorio instancia = null;
    private List<Colegiado> repositorio = new ArrayList<Colegiado>();
    private CursoRepositorio cursoRepositorio = CursoRepositorio.getInstancia();
    private CoordenadorRepositorio coordenadorRepositorio = CoordenadorRepositorio.getInstancia();
    private ProfessorRepositorio professorRepositorio = ProfessorRepositorio.getInstancia();

    private ColegiadoRepositorio(){
        repositorio.add(new Colegiado(new Date(),new Date(),cursoRepositorio.buscarCurso("Sistemas Para Internet"), coordenadorRepositorio.buscarCoordenador(professorRepositorio.buscarProfessor("123456"))));
    }

    public static ColegiadoRepositorio getInstancia(){
        if(instancia == null){
            instancia = new ColegiadoRepositorio();
        }
        return instancia;
    }

    public List<Colegiado> getRepositorio(){
        return this.repositorio;
    }

    //buscar Colegiado por Curso
    public Colegiado buscarColegiadoPorCurso(Curso curso){
        for(Colegiado colegiado : this.repositorio){
            if(colegiado.getCurso().equals(curso)){
                return colegiado;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        String saida = "";
        for(Colegiado colegiado: this.repositorio){
           saida = saida + colegiado + ";\n";
        }
        return saida;
    }
    
}
