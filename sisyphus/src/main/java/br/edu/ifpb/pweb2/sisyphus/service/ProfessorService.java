package br.edu.ifpb.pweb2.sisyphus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sisyphus.repository.ProfessorRepository;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;
import java.util.List;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> getProfessores(){
        return this.professorRepository.findAll();
    }

    public Professor getProfessorPorId(Long id){
        return this.professorRepository.findById(id).orElse(null);
    }

    public Professor salvarProfessor(Professor professor){
        return this.professorRepository.save(professor);
    }

    public void deletarProfessor(Long id){
        this.professorRepository.deleteById(id);
    }
}
