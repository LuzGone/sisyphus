package br.edu.ifpb.pweb2.sisyphus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sisyphus.repository.AlunoRepository;
import br.edu.ifpb.pweb2.sisyphus.model.Aluno;

import java.util.ArrayList;
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
    
    public List<Aluno> getAlunosComProcessos(){
        List<Aluno> alunos = new ArrayList<Aluno>();
        for (Aluno aluno : this.alunoRepository.findAll()){
            if(aluno.getListaDeProcessos() != null){
                alunos.add(aluno);
            }
        }
        return alunos;
    }

    public Aluno salvarAluno(Aluno aluno){
        System.out.println(aluno);
        return this.alunoRepository.save(aluno);
    }

    public void apagarAluno(Long id){
        this.alunoRepository.deleteById(id);
    }
}
