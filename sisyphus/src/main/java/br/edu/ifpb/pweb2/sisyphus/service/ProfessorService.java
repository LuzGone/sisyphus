package br.edu.ifpb.pweb2.sisyphus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sisyphus.repository.ProfessorRepository;
import br.edu.ifpb.pweb2.sisyphus.util.PasswordUtil;
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

    public Page<Professor> getProfessores(Pageable pageable){
        return this.professorRepository.findAll(pageable);
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
        professor.setSenha(PasswordUtil.hashPassword(professor.getSenha()));
        return this.professorRepository.save(professor);
    }

    public void deletarProfessor(Long id){
        this.professorRepository.deleteById(id);
    }

    public Professor getProfessorPorUsuario(String usuario){
        return this.professorRepository.findByUsuario(usuario);
    }
}
