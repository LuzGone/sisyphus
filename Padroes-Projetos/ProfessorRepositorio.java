import java.util.ArrayList;
import java.util.List;

public final class ProfessorRepositorio{
    private static ProfessorRepositorio instancia;
    private CursoRepositorio cursoRepositorio = CursoRepositorio.getInstancia();
    private List<Professor> listaDeProfessores = new ArrayList<Professor>();
    
    private ProfessorRepositorio(){
        listaDeProfessores.add(new Professor("Alex","83996277151","123456", cursoRepositorio.getRepositorio().get(0),"alex","123"));
        listaDeProfessores.add(new Professor("Fred","83996277152","123457", cursoRepositorio.getRepositorio().get(0),"fred","123"));
        listaDeProfessores.add(new Professor("Rodrigo","83996277153","123458", cursoRepositorio.getRepositorio().get(0),"rodrigo","123"));
        listaDeProfessores.add(new Professor("Valeria","83996277154","123459", cursoRepositorio.getRepositorio().get(0),"valeria","123"));
        listaDeProfessores.add(new Professor("Candido","83996277155","123460", cursoRepositorio.getRepositorio().get(0),"candido","123"));
    }
    
    public static ProfessorRepositorio getInstancia(){
        if(instancia == null){
            instancia = new ProfessorRepositorio();
        }
        return instancia;
    }
    
    public void adicionarProfessor(Professor professor){
        this.listaDeProfessores.add(professor);
    }
    
    public void removerProfessor(Professor professor){
        this.listaDeProfessores.remove(professor);
    }
    
    public Professor buscarProfessor(String matricula){
        for(Professor professor : this.listaDeProfessores){
            if(professor.getMatricula().equals(matricula)){
                return professor;
            }
        }
        return null;
    }
    
    public List<Professor> getListaDeProfessores(){
        return this.listaDeProfessores;
    }
    
    @Override
    public String toString(){
        String retorno = "";
        for(Professor professor : this.listaDeProfessores){
            retorno += professor + "\n";
        }
        return retorno;
    }
}
