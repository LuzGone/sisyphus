package br.edu.ifpb.pweb2.sisyphus.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.pweb2.sisyphus.model.Aluno;
import br.edu.ifpb.pweb2.sisyphus.model.Processo;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo,Long>{
    public List<Processo> findByAluno(Aluno aluno);

    public Page<Processo> findByAluno(Aluno aluno, Pageable pageable);

    public List<Processo> findByRelator(Professor professor);

    public Page<Processo> findByRelator(Professor professor, Pageable pageable);
}
