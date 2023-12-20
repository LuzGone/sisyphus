package br.edu.ifpb.pweb2.sisyphus.model;

import java.util.List;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String numero;

    private Date dataDeCriacao;

    private Date dataDeDistribuicao;

    private Date dataDoParecer;

    private byte[] documentos;

    @ManyToOne
    private Professor relator;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Colegiado colegiado;

    @ManyToOne
    @JoinColumn(name = "assunto")
    private Assunto assunto;

    @Enumerated(EnumType.STRING)
    private TipoDecisao tipoDecisao;

    @OneToMany(mappedBy = "processo")
    private List<Voto> listaDeVotos;

    @NotBlank(message="É necessário informar o motivo da abertura do processo.")
    private String textoRequerimento;

    private String justificativaRelator;

    @Enumerated(EnumType.STRING)
    private EstadoProcesso estadoProcesso;

    @ManyToOne
    private Reuniao reuniao;

    public Processo( Aluno aluno, Assunto assunto, String textoRequerimento, Colegiado colegiado) {
        this.aluno = aluno;
        this.numero = Integer.toString(this.id);
        this.estadoProcesso = EstadoProcesso.CRIADO;
        this.dataDeCriacao = new Date();
        this.assunto = assunto;
        this.textoRequerimento = textoRequerimento;
        this.colegiado = colegiado;
    }

    public Processo(Aluno aluno,Assunto assunto){
        this.aluno = aluno;
        this.assunto = assunto;
    }

    @Override
    public String toString(){
        return ""+this.numero+";"+this.aluno+";"+this.estadoProcesso;
    }

}
