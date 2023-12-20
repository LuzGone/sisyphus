import java.util.ArrayList;
import java.util.List;


public class Professor {

    protected String nome;
    
    protected String telefone;
    protected String matricula;
    protected Curso curso;
    protected String usuario;
    protected String senha;
    protected List<Processo> listaDeProcessos = new ArrayList<Processo>();
    protected List<Colegiado> listaColegiados = new ArrayList<Colegiado>();

    public Professor(String nome, String telefone, String matricula, Curso curso, String usuario, String senha) {
        this.nome = nome;
        this.telefone = telefone;
        this.matricula = matricula;
        this.curso = curso;
        this.usuario = usuario;
        this.senha = senha;
    }

    public void adicionarProcesso(Processo processo){
        this.listaDeProcessos.add(processo);
    }

    public void adicionarColegiado(Colegiado colegiado){
        this.listaColegiados.add(colegiado);
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getMatricula() {
        return matricula;
    }

    public Curso getCurso() {
        return curso;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public List<Processo> getListaDeProcessos() {
        return listaDeProcessos;
    }

    public List<Colegiado> getListaColegiados() {
        return listaColegiados;
    }

    @Override
    public String toString(){
        return this.nome;
    }

}
