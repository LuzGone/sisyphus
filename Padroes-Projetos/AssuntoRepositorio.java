import java.util.ArrayList;
import java.util.List;

public final class AssuntoRepositorio {
    private static AssuntoRepositorio instancia = null;
    private List<Assunto> repositorio = new ArrayList<Assunto>();
    
    private AssuntoRepositorio(){
        repositorio.add(new Assunto("Renovação de Matrícula"));
        repositorio.add(new Assunto("Aceleração de Disciplina"));
        repositorio.add(new Assunto("Reaproveitamento de Disiciplina"));
    }
    
    public static AssuntoRepositorio getInstancia(){
        if(instancia == null){
            instancia = new AssuntoRepositorio();
        }
        return instancia;
    }

    public List<Assunto> getRepositorio(){
        return this.repositorio;
    }

    @Override
    public String toString(){
        String saida = "";
        for(Assunto assunto: this.repositorio){
           saida = saida + assunto + ";\n";
        }
        return saida;
    }
}
