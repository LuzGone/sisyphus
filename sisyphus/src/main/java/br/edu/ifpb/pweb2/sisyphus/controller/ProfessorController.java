package br.edu.ifpb.pweb2.sisyphus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;
import br.edu.ifpb.pweb2.sisyphus.service.ProfessorService;

@Controller
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public List<Professor> listarProfessores() {
        return professorService.listarProfessores();
    }

    @GetMapping("/{id}")
    public Professor obterProfessor(@PathVariable int id) {
        return professorService.encontrarProfessorPorId(id);
    }

    @PostMapping
    public Professor criarProfessor(@RequestBody Professor professor) {
        return professorService.criarProfessor(professor);
    }

    @DeleteMapping("/{id}")
    public void deletarProfessor(@PathVariable int id) {
        professorService.deletarProfessor(id);
    }
    
}

