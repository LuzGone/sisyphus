package sisyphus.src.models;

public class Curso {

    private String nome;

    public Curso(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return nome;
    }

    public void setCurso(String nome) {
        this.nome = nome;
    }
    

    @Override
    public String toString(){
        return this.nome;
    }

}
