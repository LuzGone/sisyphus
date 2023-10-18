package br.edu.ifpb.pweb2.sisyphus.repository;

import br.edu.ifpb.pweb2.sisyphus.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    
}
