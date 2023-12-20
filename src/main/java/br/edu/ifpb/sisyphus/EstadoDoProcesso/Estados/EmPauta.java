package src.main.java.br.edu.ifpb.sisyphus.EstadoDoProcesso.Estados;

public class EstadoEmPauta implements EstadoProcesso{

    private Processo processo;

    public EstadoEmPauta(Processo processo) {
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
        System.out.println("Processo não pode ser colocado em julgamento");
    }

    @Override
    public void votar(Voto voto) {
        this.processo.listaDeVotos.add(voto);
    }

    @Override
    public void julgarProcesso() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'julgarProcesso'");
    }
    
}
