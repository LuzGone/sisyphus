package br.edu.ifpb.pweb2.sisyphus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ifpb.pweb2.sisyphus.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno,Long> {
    
}
