package br.edu.ifpb.pweb2.sisyphus.repository;

import br.edu.ifpb.pweb2.sisyphus.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    
}