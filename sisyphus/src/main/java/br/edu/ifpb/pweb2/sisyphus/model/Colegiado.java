package br.edu.ifpb.pweb2.sisyphus.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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

    @NotBlank(message="Campo obrigatório!")
    private String curso;

    @ManyToMany
    private List<Professor> membros;

    @OneToMany(mappedBy = "colegiado")
    private List<Processo> processos;

    public Colegiado(Date dataInicio, Date dataFim, String descricao, String portaria, String curso) {
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


}
