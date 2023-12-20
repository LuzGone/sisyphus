import java.util.List;
public class EmJulgamento implements EstadoProcesso{
    private Processo processo;

    public EmJulgamento(Processo processo) {
        this.processo = processo;
    }
    
    @Override
    public void proximoEstado(EstadoProcesso estadoProcesso) {
        this.processo.alterarEstadoProcesso(estadoProcesso);
    }

    //1º - Atribuir Processo a um Relator
    @Override
    public void atribuirProcessoAoRelator(Professor relator) throws Exception{
        if(this.processo.getEstadoProcesso() instanceof Criado){
            this.proximoEstado(new Distribuido(this.processo));
            System.out.println("Processo atribuido ao relator " + relator);
        }else{
            throw new Exception("Processo só pode ser Atribuido enquanto ainda estiver como Criado.");	
        }
    }

    //2º - Adicionar Processo a pauta de uma Reuniao
    @Override
    public void pautarProcesso() throws Exception{
        if(this.processo.getEstadoProcesso() instanceof Distribuido){
            this.proximoEstado(new EmPauta(this.processo));
            System.out.println("Processo pautado");
        }else{
            throw new Exception("Processo só pode ser Pautado enquanto estiver como Distribuido.");
        }
    };

    //3º - Relator adiciona sua Decisão

    //4º - Apregoar Para Julgamento
    @Override
    public void colocarEmJulgamento() throws Exception{
        if(this.processo.getEstadoProcesso() instanceof EmPauta){
            this.proximoEstado(new EmJulgamento(this.processo));
            System.out.println("Processo em julgamento");
        }else{
            throw new Exception("Processo só pode ser colocado em julgamento enquanto estiver como EmPauta.");
        }
    }

    //5º - Julgar Processo
    @Override
    public void julgarProcesso(List<Voto> listaDeVotos) throws Exception{
        if(this.processo.getEstadoProcesso() instanceof EmJulgamento){
            this.proximoEstado(new Julgado(this.processo));
            System.out.println("Processo julgado");
        }else{
            throw new Exception("Processo só pode ser julgado enquanto estiver como EmJulgamento.");
        }
    }

    @Override
    public String toString(){
        return "Em julgamento";
    };
    
}
