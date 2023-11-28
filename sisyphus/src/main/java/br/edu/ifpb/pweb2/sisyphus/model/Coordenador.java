package br.edu.ifpb.pweb2.sisyphus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Coordenador{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message="Campo obrigatório!")
    private String curso;
    
    @OneToOne
    @JoinColumn(name="professor")
    private Professor professor;

    public Coordenador(Professor professor, String curso){
        this.professor = professor;
        this.curso = curso;
    }

    public void delegarProcesso(Processo processo, Professor professor){
        professor.adicionarProcesso(processo);
    }

}
