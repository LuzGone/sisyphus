package src.main.java.br.edu.ifpb.sisyphus.EstadoDoProcesso.Estados;

public class EstadoJulgado implements EstadoProcesso{

    private Processo processo;

    public EstadoJulgado(Processo processo) {
        this.processo = processo;
    }

    @Override
    public void atualizarProcesso(String justificativaRelator, TipoDecisao tipoDecisao) {
        this.processo.justificativaRelator = justificativaRelator;
        this.processo.tipoDecisao = tipoDecisao;
    }

    @Override
    public void atribuirProcesso(Professor relator, Colegiado colegiado) {
        System.out.println("Processo já foi atribuído");
    }

    @Override
    public void colocarEmJulgamento() {
        System.out.println("Processo não pode ser colocado em julgamento");
    }

    @Override
    public void votar(Voto voto) {
        System.out.println("Processo não pode ser votado");
    }

    @Override
    public void julgarProcesso() {
        System.out.println("Processo já foi julgado");
    }
    
}
