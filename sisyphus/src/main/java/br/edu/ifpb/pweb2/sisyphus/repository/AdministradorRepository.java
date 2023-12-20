package br.edu.ifpb.pweb2.sisyphus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.pweb2.sisyphus.model.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador,Long>{
    Administrador findByUsuario(String usuario);
}
