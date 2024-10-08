package br.edu.ifpb.pweb2.sisyphus.model;

import java.util.List;

import br.edu.ifpb.pweb2.sisyphus.validator.OnlyNumberMat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Data
@NoArgsConstructor
@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message="É necessário informar o nome do Aluno.")
    private String nome;
    
    @NotBlank(message="É necessário informar o telefone do Aluno.")
    private String telefone;

    @OnlyNumberMat(message="A matrícula inserida é inválida. Por favor, insira apenas números na matrícula.\" ")
    
    @NotBlank(message="É necessário informar a matrícula do Aluno.")
    @Pattern(regexp= "[0-9]{11}", message="Matrícula deve conter exatamente 11 números!")
    private String matricula;

    @ManyToOne
    @JoinColumn(name="curso")
    private Curso curso;

    @NotBlank(message="É necessário informar um nome de usuário para o Aluno.")
    private String usuario;

    @NotBlank(message="É necessário informar a senha para o Aluno.")
    @Size(min=3, max=60 ,message="A senha deverá ter pelo menos 3 caracteres e no máximo 60")
    private String senha;

    @OneToOne
    @JoinColumn(name = "username")
    private User user;

    @OneToMany(mappedBy = "aluno")
    private List<Processo> listaDeProcessos;

    public void adicionarProcesso(Processo processo){
        this.listaDeProcessos.add(processo);
    }

    @Override
    public String toString(){
        return "Aluno: "+this.nome;
    }
}
