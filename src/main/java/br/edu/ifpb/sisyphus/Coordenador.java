package src.main.java.br.edu.ifpb.sisyphus;


public class Coordenador{

    private Professor professor;

    public Coordenador(Professor professor){
        this.professor = professor;
    }

    public void delegarProcesso(Processo processo, Professor professor){
        professor.adicionarProcesso(processo);
    }

    @Override
    public String toString(){
        return this.professor.toString() + " - " + this.professor.curso;
    }
}
