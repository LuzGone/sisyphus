package br.edu.ifpb.pweb2.sisyphus.repository;

import br.edu.ifpb.pweb2.sisyphus.model.Assunto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssuntoRepository extends JpaRepository<Assunto, Integer> {
    
}