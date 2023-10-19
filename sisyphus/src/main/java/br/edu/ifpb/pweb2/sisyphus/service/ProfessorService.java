package br.edu.ifpb.pweb2.sisyphus.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ifpb.pweb2.sisyphus.repository.ProfessorRepository;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> listarProfessores() {
        return professorRepository.findAll();
    }

    public Professor encontrarProfessorPorId(int id) {
        return professorRepository.findById(id).orElse(null);
    }

    public Professor criarProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public void deletarProfessor(int id) {
        professorRepository.deleteById(id);
    }

}

