import java.util.ArrayList;
import java.util.List;

public final class CoordenadorRepositorio {
    private static CoordenadorRepositorio instancia = null;
    private List<Coordenador> repositorio = new ArrayList<Coordenador>();
    private ProfessorRepositorio professorRepositorio = ProfessorRepositorio.getInstancia();
    
    private CoordenadorRepositorio(){
        repositorio.add(new Coordenador(professorRepositorio.buscarProfessor("123456")));
    }

    public static CoordenadorRepositorio getInstancia(){
        if(instancia == null){
            instancia = new CoordenadorRepositorio();
        }
        return instancia;
    }

    //Buscar Coordenador
    public Coordenador buscarCoordenador(Professor professor){
        for(Coordenador coordenador : this.repositorio){
            if(coordenador.getProfessor().equals(professor)){
                return coordenador;
            }
        }
        return null;
    }

    public List<Coordenador> getRepositorio(){
        return this.repositorio;
    }

    @Override
    public String toString(){
        String saida = "";
        for(Coordenador coordenador: this.repositorio){
           saida = saida + coordenador + ";\n";
        }
        return saida;
    }

}
