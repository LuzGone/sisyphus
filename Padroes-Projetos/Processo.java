import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class Processo {

    private String numero;
    private Date dataDeCriacao;
    private Date dataDeDistribuicao;
    private Date dataDoParecer;
    private Professor relator;
    private Aluno aluno;
    private Colegiado colegiado;
    private Assunto assunto;
    private TipoDecisao tipoDecisao;
    private List<Voto> listaDeVotos = new ArrayList<Voto>();
    private String textoRequerimento;
    private String justificativaRelator;
    private EstadoProcesso estadoProcesso;
    private Reuniao reuniao;

    public Processo( 
        Aluno aluno, 
        Assunto assunto, 
        String textoRequerimento, 
        Colegiado colegiado) 
        {
        this.numero = ""+new Date().getTime();
        this.dataDeCriacao = new Date();
        this.aluno = aluno;
        this.estadoProcesso = new Criado(this);
        this.assunto = assunto;
        this.textoRequerimento = textoRequerimento;
        this.colegiado = colegiado;
    }

    public void alterarEstadoProcesso(EstadoProcesso estadoProcesso){
        this.estadoProcesso = estadoProcesso;
    }

    public EstadoProcesso getEstadoProcesso(){
        return this.estadoProcesso;
    }

    //Passo a Passo do Processo;
    //1º - Atribuir Processo a um Relator
    public void atribuirProcessoAoRelator(Professor relator) throws Exception{
        try{
            this.estadoProcesso.atribuirProcessoAoRelator(relator);
            this.relator = relator;
            this.relator.adicionarProcesso(this);
        }catch(Exception e){
            throw e;
        }
    };

    //2º - Adicionar Processo a pauta de uma Reuniao
    public void pautarProcesso() throws Exception{
        try{
            this.estadoProcesso.pautarProcesso();
        }catch(Exception e){
            throw e;
        }
    };

    //3º - Relator adiciona sua Decisão
    public void adicionarDecisao(String justificativaRelator, TipoDecisao tipoDecisao) throws Exception{
        if(this.getEstadoProcesso() instanceof EmPauta){
            this.justificativaRelator = justificativaRelator;
            this.tipoDecisao = tipoDecisao;
        }else{
            throw new Exception("Processo só pode ter uma decisão enquanto estiver como EmPauta.");
        }
    };

    //4º - Apregoar Para Julgamento
    public void colocarEmJulgamento() throws Exception{
        try{
            this.estadoProcesso.colocarEmJulgamento();
        }catch(Exception e){
            throw e;
        }
    }

    //5º - Julgar Processo
    public void julgarProcesso(List<Voto> listaDeVotos) throws Exception{
        try{
            this.estadoProcesso.julgarProcesso(listaDeVotos);
            this.listaDeVotos = listaDeVotos;
            int votosComRelator = 1;
            int votosDivergentes = 0;
            for(Voto voto : listaDeVotos){
                if(voto.getTipoVoto() == TipoVoto.DIVERGENTE){
                    votosDivergentes++;
                }
                if(voto.getTipoVoto() == TipoVoto.COM_RELATOR){
                    votosComRelator++;
                }
            }
            if(votosDivergentes > votosComRelator){
                if(this.tipoDecisao == TipoDecisao.DEFERIDO){
                    this.tipoDecisao = TipoDecisao.INDEFERIDO;
                }else if(this.tipoDecisao == TipoDecisao.INDEFERIDO){
                    this.tipoDecisao = TipoDecisao.DEFERIDO;
                }
            }
            System.out.println("Votos Com Relator: "+votosComRelator);
            System.out.println("Votos Divergentes: "+votosDivergentes);
            System.out.println("Decisão Final: "+this.tipoDecisao);
        }catch(Exception e){
            throw e;
        }
    }


    @Override
    public String toString(){
        String texto = "";
        texto += this.numero+";";
        texto += this.aluno+";";
        texto += this.estadoProcesso.toString()+";";
        if(this.tipoDecisao != null){
            texto += this.tipoDecisao.toString()+";";
        }
        return texto;
    }
}
