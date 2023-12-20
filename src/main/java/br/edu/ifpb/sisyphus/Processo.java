package src.main.java.br.edu.ifpb.sisyphus;

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
        this.estadoProcesso = new EstadoCriado(this);
        this.assunto = assunto;
        this.textoRequerimento = textoRequerimento;
        this.colegiado = colegiado;

    }

    public void atualizarProcesso(String justificativaRelator, TipoDecisao tipoDecisao){
        this.estadoProcesso.atualizarProcesso(justificativaRelator, tipoDecisao);
    }

    public void testandoProcesso(Processo processo){
        System.out.println(this.listaDeVotos);
    }

    public void atribuirProcesso(Professor relator, Colegiado colegiado) {
        this.estadoProcesso.atribuirProcesso(relator, colegiado);
    }

    // colocar o processo em julgamento
    public void colocarEmJulgamento() {
        this.estadoProcesso.colocarEmJulgamento();
    }

    public void votar(Voto voto) {
        this.estadoProcesso.votar(voto);
    }

    public void julgarProcesso() {
        this.estadoProcesso.julgarProcesso();
    }

    @Override
    public String toString(){
        return ""+this.numero+";"+this.aluno+";"+this.estadoProcesso.toString();
    }
}
