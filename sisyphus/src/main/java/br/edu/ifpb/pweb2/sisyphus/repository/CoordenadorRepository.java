package br.edu.ifpb.pweb2.sisyphus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.pweb2.sisyphus.model.Coordenador;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;

@Repository
public interface CoordenadorRepository extends JpaRepository<Coordenador,Long> {
    Coordenador findByProfessor(Professor professor);
}
