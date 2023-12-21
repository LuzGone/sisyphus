package br.edu.ifpb.pweb2.sisyphus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.pweb2.sisyphus.model.Colegiado;
import br.edu.ifpb.pweb2.sisyphus.model.Reuniao;

@Repository
public interface ReuniaoRepository extends JpaRepository<Reuniao,Long>{
    Page<Reuniao> findByColegiado(Colegiado colegiado, Pageable pageable);
}
