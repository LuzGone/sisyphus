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
    private Date dataCriacao;
    private Date dataDistribuicao;
    private Date dataParecer;
    private byte[] parecer;

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

    private String textoRequerimento;

    @Enumerated(EnumType.STRING)
    private EstadoProcesso estadoProcesso;

    public Processo( Aluno aluno, Assunto assunto, String textoRequerimento, Colegiado colegiado) {
        this.aluno = aluno;
        this.numero = Integer.toString(this.id);
        this.estadoProcesso = EstadoProcesso.CRIADO;
        this.dataCriacao = new Date();
        this.assunto = assunto;
        this.textoRequerimento = textoRequerimento;
        this.colegiado = colegiado;
    }

    public Processo(Aluno aluno,Assunto assunto){
        this.aluno = aluno;
        this.assunto = assunto;
    }

}
