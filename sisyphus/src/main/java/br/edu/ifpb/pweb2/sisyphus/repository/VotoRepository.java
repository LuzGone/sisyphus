package br.edu.ifpb.pweb2.sisyphus.repository;

import br.edu.ifpb.pweb2.sisyphus.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Integer> {
    
}