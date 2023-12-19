package sisyphus.src.models;

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

    public Processo( Aluno aluno, Assunto assunto, 
                     String textoRequerimento, Colegiado colegiado, Reuniao reuniao, 
                     Professor relator, TipoDecisao tipoDecisao, String justificativaRelator) {
        this.numero = ""+new Date().getTime();
        this.dataDeCriacao = new Date();
        this.relator = relator;
        this.aluno = aluno;
        //this.estadoProcesso = EstadoProcesso.CRIADO;
        this.assunto = assunto;
        this.textoRequerimento = textoRequerimento;
        this.colegiado = colegiado;

    }

    public void atualizarProcesso(String justificativaRelator, TipoDecisao tipoDecisao){
        this.justificativaRelator = justificativaRelator;
        this.tipoDecisao = tipoDecisao;
    }

    public void testandoProcesso(Processo processo){
        System.out.println(this.listaDeVotos);
    }

    public void atribuirProcesso(Professor relator, Colegiado colegiado) {
        this.relator = relator;
        this.colegiado = colegiado;
        //mudar estado para distribuido
        this.dataDeDistribuicao = new Date();
    }

    // colocar o processo em julgamento
    public void colocarEmJulgamento() {
        //mudar estado para julgamento
    }

    public void votar(Voto voto) {
        this.listaDeVotos.add(voto);
    }

    public void julgarProcesso() {
        int comRelator = 1;
        int divergente = 0;
        for(Voto voto: this.listaDeVotos){
            if(voto.getTipoVoto() == TipoVoto.DIVERGENTE){
                divergente++;
            }
            if(voto.getTipoVoto() == TipoVoto.COM_RELATOR){
                comRelator++;
            }
        }
        if(divergente > comRelator){
            this.tipoDecisao = TipoDecisao.INDEFERIDO;
        }else{
            this.tipoDecisao = TipoDecisao.DEFERIDO;
        }
        // mudar estado para julgado
        this.dataDoParecer = new Date();
    }

    @Override
    public String toString(){
        return ""+this.numero+";"+this.aluno+";"+this.estadoProcesso.toString();
    }
}
