package br.edu.ifpb.pweb2.sisyphus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long> {
    Professor findByUsuario(String usuario);
}
