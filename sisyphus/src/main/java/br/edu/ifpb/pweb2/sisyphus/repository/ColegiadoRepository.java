package br.edu.ifpb.pweb2.sisyphus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.pweb2.sisyphus.model.Colegiado;
import br.edu.ifpb.pweb2.sisyphus.model.Coordenador;

@Repository
public interface ColegiadoRepository extends JpaRepository<Colegiado,Long>{
    public List<Colegiado> findByCoordenador(Coordenador coordenador);
}
