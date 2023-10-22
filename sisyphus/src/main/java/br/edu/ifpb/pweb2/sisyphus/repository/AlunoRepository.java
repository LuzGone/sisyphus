package br.edu.ifpb.pweb2.sisyphus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ifpb.pweb2.sisyphus.model.Aluno;
import java.util.List;


@Repository
public interface AlunoRepository extends JpaRepository<Aluno,Long> {
    public List<Aluno> findById(int id);
    public List<Aluno> findByMatricula(String matricula);
}
