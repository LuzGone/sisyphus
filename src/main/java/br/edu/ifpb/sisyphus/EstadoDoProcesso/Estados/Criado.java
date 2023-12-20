package src.main.java.br.edu.ifpb.sisyphus.EstadoDoProcesso.Estados;

import java.util.Date;


public class EstadoCriado implements EstadoProcesso {

    private Processo processo;

    public EstadoCriado(Processo processo) {
        this.processo = processo;
    }

    @Override
    public void atualizarProcesso(String justificativaRelator, TipoDecisao tipoDecisao) {
        System.out.println("Processo não pode ser atualizado");
    }

    @Override
    public void atribuirProcesso(Professor relator, Colegiado colegiado) {
        this.processo.relator = relator;
        this.processo.colegiado = colegiado;
        this.processo.dataDeDistribuicao = new Date();
        this.processo.estadoProcesso = new EstadoDistribuido(this.processo);
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'julgarProcesso'");
    }


}
    
