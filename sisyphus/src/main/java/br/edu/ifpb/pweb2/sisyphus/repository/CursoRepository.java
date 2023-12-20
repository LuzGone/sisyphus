package br.edu.ifpb.pweb2.sisyphus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.pweb2.sisyphus.model.Curso;
import java.lang.Long;

public interface CursoRepository extends JpaRepository<Curso, Long>{
    
}
