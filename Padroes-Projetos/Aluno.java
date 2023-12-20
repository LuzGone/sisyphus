import java.util.ArrayList;
import java.util.List;

public class Aluno {

    private String nome;
    private String telefone;
    private String matricula;
    private Curso curso;
    private String usuario;
    private String senha;
    private List<Processo> listaDeProcessos = new ArrayList<Processo>();

    public Aluno(String nome, String telefone, String matricula, Curso curso, String usuario, String senha) {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Processo> getListaDeProcessos() {
        return listaDeProcessos;
    }

    public void setListaDeProcessos(List<Processo> listaDeProcessos) {
        this.listaDeProcessos = listaDeProcessos;
    }
    
    

    @Override
    public String toString(){
        return "Aluno: "+this.nome;
    }
}
