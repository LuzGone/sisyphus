package br.edu.ifpb.pweb2.sisyphus.model;

import java.util.List;

import br.edu.ifpb.pweb2.sisyphus.validator.OnlyNumberMat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message="É necessário informar o nome do Professor.")
    protected String nome;
    
    @NotBlank(message="É necessário informar o telefone do Professor.")
    protected String telefone;

    @OnlyNumberMat(message="A matrícula inserida é inválida. Por favor, insira apenas números na matrícula.\" ")
    
    @NotBlank(message="É necessário informar a matrícula do Professor.")
    @Pattern(regexp= "[0-9]{6}", message="Matrícula deve conter exatamente 6 números!")
    protected String matricula;

    @ManyToOne
    @JoinColumn(name = "curso")
    protected Curso curso;
    
    @NotBlank(message="É necessário informar um nome de usuário para o Professor.")
    protected String usuario;

    @NotBlank(message="É necessário informar a senha para o Professor.")
    @Size(min=3, max=42 ,message="A senha deverá ter pelo menos 3 caracteres e no máximo 42")
    protected String senha;

    @OneToMany(mappedBy = "relator")
    protected List<Processo> listaDeProcessos;

    @ManyToMany(mappedBy = "membros")
    protected List<Colegiado> listaColegiados;

    public void adicionarProcesso(Processo processo){
        this.listaDeProcessos.add(processo);
    }

    public void adicionarColegiado(Colegiado colegiado){
        this.listaColegiados.add(colegiado);
    }

    @Override
    public String toString(){
        return this.nome;
    }

}
