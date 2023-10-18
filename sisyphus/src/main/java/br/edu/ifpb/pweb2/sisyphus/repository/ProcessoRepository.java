package br.edu.ifpb.pweb2.sisyphus.repository;

import br.edu.ifpb.pweb2.sisyphus.model.Processo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoRepository extends JpaRepository<Processo, Integer> {
    
}
