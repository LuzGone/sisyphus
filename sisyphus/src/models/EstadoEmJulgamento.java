package sisyphus.src.models;

import java.util.Date;
public class EstadoEmJulgamento implements EstadoProcesso{
    private Processo processo;

    public EstadoEmJulgamento(Processo processo) {
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
        int comRelator = 1;
        int divergente = 0;
        for(Voto voto: this.processo.listaDeVotos){
            if(voto.getTipoVoto() == TipoVoto.DIVERGENTE){
                divergente++;
            }
            if(voto.getTipoVoto() == TipoVoto.COM_RELATOR){
                comRelator++;
            }
        }
        if(divergente > comRelator){
            this.processo.tipoDecisao = TipoDecisao.INDEFERIDO;
        }else{
            this.processo.tipoDecisao = TipoDecisao.DEFERIDO;
        }
        // mudar estado para julgado
        this.processo.dataDoParecer = new Date();
    }
    
}
