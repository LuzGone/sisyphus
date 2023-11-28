package br.edu.ifpb.pweb2.sisyphus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sisyphus.repository.ProfessorRepository;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> getProfessores(){
        return this.professorRepository.findAll();
    }

    public List<Professor> getProfessoresComColegiado(){
        List<Professor> professores = new ArrayList<Professor>();
        for (Professor professor : this.professorRepository.findAll()){
            if(professor.getListaColegiados() != null){
                professores.add(professor);
            }
        }
        return professores;
    }

    public List<Professor> getProfessoresComProcessos(){
        List<Professor> professores = new ArrayList<Professor>();
        for (Professor professor : this.professorRepository.findAll()){
            if(professor.getListaDeProcessos().size() > 0){
                professores.add(professor);
            }
        }
        return professores;
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
