public class Voto {

    private Professor professor;
    private Processo processo;
    private TipoVoto tipoVoto;

    public Voto(Professor professor, Processo processo, TipoVoto tipoVoto){
        this.professor = professor;
        this.processo = processo;
        this.tipoVoto = tipoVoto;
    }

    public TipoVoto getTipoVoto() {
        return this.tipoVoto;
    }

}
