import java.util.Date;
import java.util.List;


public class Reuniao {

    private String codigo;
    private Date dataReuniao;
    private StatusReuniao status;
    private List<Processo> processos;
    private Colegiado colegiado;

    public Reuniao(Date dataReuniao, List<Processo> processos, Colegiado colegiado) {
        this.codigo = new Date().getTime()+"/"+colegiado.getCurso();
        this.dataReuniao = dataReuniao;
        this.status = new Programada(this);
        this.processos = processos;
        this.colegiado = colegiado;
    }

    public void pautarProcessosParaJulgamento(){
        try{
            for (Processo processo : processos) {
                processo.pautarProcesso();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    //1º - Iniciar Reuniao
    public void inicarReuniao(){
        try{
            this.status.inicarReuniao();
            for (Processo processo : processos) {
                processo.colocarEmJulgamento();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    };

    //2º - Encerrar Reuniao
    public void encerrarReuniao(){
        try{
            this.status.encerrarReuniao();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    };

    public StatusReuniao getStatus() {
        return status;
    }

    public void setStatus(StatusReuniao status) {
        this.status = status;
    }

    public void adicionarProcesso(Processo processo) {
        this.processos.add(processo);
    }

    @Override
    public String toString(){
        return "Reunião de "+ this.colegiado+" - "+ this.dataReuniao;
    }

    
}
