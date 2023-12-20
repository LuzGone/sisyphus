package br.edu.ifpb.pweb2.sisyphus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
@Entity
public class Assunto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message="É necessário informar do que o assunto se trata.")
    private String nomeDoAssunto;

    public Assunto(String nomeDoAssunto) {
        this.nomeDoAssunto = nomeDoAssunto;
    }

    @Override
    public String toString(){
        return this.nomeDoAssunto;
    }

}
