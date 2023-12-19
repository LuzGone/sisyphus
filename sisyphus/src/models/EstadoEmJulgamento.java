package sisyphus.src.models;
public class EstadoEmJulgamento implements EstadoProcesso{
    private Processo processo;

    public EstadoEmJulgamento(Processo processo) {
        this.processo = processo;
    }
    
    @Override
    public void atualizarProcesso(String justificativaRelator, TipoDecisao tipoDecisao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizarProcesso'");
    }

    @Override
    public void atribuirProcesso(Professor relator, Colegiado colegiado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atribuirProcesso'");
    }

    @Override
    public void colocarEmJulgamento() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'colocarEmJulgamento'");
    }

    @Override
    public void votar(Voto voto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'votar'");
    }

    @Override
    public void julgarProcesso() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'julgarProcesso'");
    }
    
}
