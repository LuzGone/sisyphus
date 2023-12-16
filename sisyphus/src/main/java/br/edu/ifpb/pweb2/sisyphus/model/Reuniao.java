package br.edu.ifpb.pweb2.sisyphus.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Reuniao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message="Campo obrigatório!")
    private Date dataReuniao;

    @Enumerated(EnumType.STRING)
    private StatusReuniao status;

    @OneToMany(mappedBy = "reuniao")
    private List<Processo> processos;

    @ManyToOne
    private Colegiado colegiado;

    public Reuniao(Colegiado colegiado,List<Processo> processos){
        this.colegiado = colegiado;
        this.processos = processos;
        this.status = StatusReuniao.PROGRAMADA;
    }

    public void adicionarProcesso(Processo processo) {
        this.processos.add(processo);
    }

    @Override
    public String toString(){
        return "Reunião de "+ this.colegiado+" - "+ this.dataReuniao;
    }

    
}
