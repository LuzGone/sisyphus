package sisyphus.src.models;

import java.util.Date;
import java.util.List;


public class Reuniao {

    private String codigo;
    private Date dataReuniao;
    private StatusReuniao status;
    private List<Processo> processos;
    private Colegiado colegiado;

    public Reuniao(String codigo, Date dataReuniao, StatusReuniao status, List<Processo> processos, Colegiado colegiado) {
        this.codigo = codigo;
        this.dataReuniao = dataReuniao;
        this.status = status;
        this.processos = processos;
        this.colegiado = colegiado;
    }

    public void adicionarProcesso(Processo processo) {
        this.processos.add(processo);
    }

    @Override
    public String toString(){
        return "Reunião de "+ this.colegiado+" - "+ this.dataReuniao;
    }

    
}
