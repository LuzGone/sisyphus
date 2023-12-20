package br.edu.ifpb.pweb2.sisyphus.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity
public class Colegiado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataDoInicio;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataDoFim;

    @NotBlank(message="É necessário informar a descrição do Colegiado.")
    private String descricao;

    @OneToOne
    @JoinColumn(name = "curso")
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
