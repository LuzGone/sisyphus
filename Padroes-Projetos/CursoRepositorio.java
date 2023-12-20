import java.util.ArrayList;
import java.util.List;

public final class CursoRepositorio {
    private static CursoRepositorio instancia = null;
    private List<Curso> repositorio = new ArrayList<Curso>();
    
    private CursoRepositorio(){
        repositorio.add(new Curso("Sistemas Para Internet"));
        repositorio.add(new Curso("Redes de Computadores"));
        repositorio.add(new Curso("Engenharia de Software"));
    }
    
    public static CursoRepositorio getInstancia(){
        if(instancia == null){
            instancia = new CursoRepositorio();
        }
        return instancia;
    }

    //Buscar Curso
    public Curso buscarCurso(String nome){
        for(Curso curso : this.repositorio){
            if(curso.getCurso().equals(nome)){
                return curso;
            }
        }
        return null;
    }

    public List<Curso> getRepositorio(){
        return this.repositorio;
    }

    @Override
    public String toString(){
        String saida = "";
        for(Curso curso: this.repositorio){
           saida = saida + curso + ";\n";
        }
        return saida;
    }
}
