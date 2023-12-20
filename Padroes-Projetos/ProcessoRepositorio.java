import java.util.ArrayList;
import java.util.List;

public final class ProcessoRepositorio{
    private static ProcessoRepositorio instancia = null;
    private List<Processo> repositorio;

    private ProcessoRepositorio(){
        this.repositorio = new ArrayList<Processo>();
    }

    public static ProcessoRepositorio getInstancia(){
        if(instancia == null){
            instancia = new ProcessoRepositorio();
        }
        return instancia;
    }

    public List<Processo> getRepositorio(){
        return this.repositorio;
    }

    //Adicionar Processo
    public void adicionarProcesso(Processo processo){
        this.repositorio.add(processo);
    }

    @Override
    public String toString(){
        String saida = "";
        for(Processo processo: this.repositorio){
           saida = saida + processo + ";\n";
        }
        return saida;
    }
}
