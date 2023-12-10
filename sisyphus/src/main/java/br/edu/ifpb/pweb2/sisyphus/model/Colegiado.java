package br.edu.ifpb.pweb2.sisyphus.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity
public class Colegiado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date dataInicio;
    private Date dataFim;

    @NotBlank(message="Campo obrigatório!")
    private String descricao;

    private String portaria;

    @Enumerated(EnumType.STRING)
    private Curso curso;

    @OneToOne
    @JoinColumn(name="coordenador")
    private Coordenador coordenador;


    @ManyToMany
    private List<Professor> membros;

    @OneToMany(mappedBy = "colegiado")
    private List<Processo> processos;

    @OneToMany(mappedBy = "colegiado")
    private List<Reuniao> reuniaos;

    public Colegiado(Date dataInicio, Date dataFim, String descricao, String portaria, Curso curso) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.descricao = descricao;
        this.portaria = portaria;
        this.curso = curso;
    }

    public Colegiado(List<Professor> professores){
        this.membros = professores;
    }

    @Override
    public String toString(){
        return "Colegiado de " + this.curso;
    }

    public void adicionarReuniao(Reuniao reuniao){
        this.reuniaos.add(reuniao);
    }

    public void adicionarProcesso(Processo processo){
        this.processos.add(processo);
    }


}
