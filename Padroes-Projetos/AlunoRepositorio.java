import java.util.ArrayList;
import java.util.List;

public final class AlunoRepositorio {
    private static AlunoRepositorio instancia = null;
    private List<Aluno> alunos = new ArrayList<Aluno>();
    private CursoRepositorio cursoRepositorio = CursoRepositorio.getInstancia();
    public AlunoRepositorio(){
        alunos.add(new Aluno("Luiz Gonzaga", "83996277101", "20211370040", cursoRepositorio.getRepositorio().get(0), "luiz", "123"));
        alunos.add(new Aluno("Louise Fernandes", "83996277102", "20211370041", cursoRepositorio.getRepositorio().get(0), "louise", "123"));
        alunos.add(new Aluno("Jardielen de Sousa", "83996277103", "20211370042", cursoRepositorio.getRepositorio().get(0), "jard", "123"));
        alunos.add(new Aluno("Gabriel Macaúbas", "83996277104", "20211370043", cursoRepositorio.getRepositorio().get(0), "caubas", "123"));

    }

    public List<Aluno> getRepositorio(){
        return this.alunos;
    }

    //Adicionar Aluno
    public void adicionarAluno(Aluno aluno){
        this.alunos.add(aluno);
    }

    //Remover Aluno

    public void removerAluno(Aluno aluno){
        this.alunos.remove(aluno);
    }

    //Buscar Aluno por Matricula
    public Aluno buscarAluno(String matricula){
        for(Aluno aluno : this.alunos){
            if(aluno.getMatricula().equals(matricula)){
                return aluno;
            }
        }
        return null;
    }

    public static AlunoRepositorio getInstancia(){
        if(instancia == null){
            instancia = new AlunoRepositorio();
        }
        return instancia;
    }

    @Override
    public String toString(){
        String saida = "";
        for(Aluno aluno: this.alunos){
            saida = saida + aluno + ";\n";
        }
        return saida;
    }
}
