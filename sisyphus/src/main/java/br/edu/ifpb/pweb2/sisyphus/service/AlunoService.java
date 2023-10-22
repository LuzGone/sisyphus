package br.edu.ifpb.pweb2.sisyphus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sisyphus.repository.AlunoRepository;
import br.edu.ifpb.pweb2.sisyphus.model.Aluno;
import java.util.List;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> getAlunos(){
        return this.alunoRepository.findAll();
    }

    public Aluno getAlunoPorId(Long id){
        return this.alunoRepository.findById(id).orElse(null);
    }

    public Aluno salvarAluno(Aluno aluno){
        return this.alunoRepository.save(aluno);
    }
}
