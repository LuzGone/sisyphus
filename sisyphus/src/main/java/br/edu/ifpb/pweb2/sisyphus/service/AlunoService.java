package br.edu.ifpb.pweb2.sisyphus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ifpb.pweb2.sisyphus.model.Aluno;
import br.edu.ifpb.pweb2.sisyphus.repository.AlunoRepository;
import java.util.List;

@Service
public class AlunoService {
    
    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> listarTodosAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno encontrarAlunoPorId(int id) {
        return alunoRepository.findById(id).orElse(null);
    }

    public Aluno criarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public void deletarAluno(int id) {
        alunoRepository.deleteById(id);
    }
}
