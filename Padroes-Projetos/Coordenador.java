public class Coordenador{

    private Professor professor;

    public Coordenador(Professor professor){
        this.professor = professor;
    }

    public void delegarProcesso(Processo processo, Professor professor){
        try{
            processo.atribuirProcessoAoRelator(professor);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Professor getProfessor() {
        return professor;
    }

    @Override
    public String toString(){
        return this.professor.toString() + " - " + this.professor.curso;
    }
}
