package src.main.java.br.edu.ifpb.sisyphus.EstadoDoProcesso.Estados;

public class EstadoDistribuido implements EstadoProcesso{
    private Processo processo;

    public EstadoDistribuido(Processo processo) {
        this.processo = processo;
    }

    @Override
    public void atualizarProcesso(String justificativaRelator, TipoDecisao tipoDecisao) {
        System.out.println("Processo não pode ser atualizado");
    }

    @Override
    public void atribuirProcesso(Professor relator, Colegiado colegiado) {
        System.out.println("Processo já foi atribuído");
    }

    @Override
    public void colocarEmJulgamento() {
        this.processo.estadoProcesso = new EstadoEmJulgamento(this.processo);
    }

    @Override
    public void votar(Voto voto) {
        System.out.println("Processo não pode ser votado");
    }

    @Override
    public void julgarProcesso() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'julgarProcesso'");
    }
    
}
