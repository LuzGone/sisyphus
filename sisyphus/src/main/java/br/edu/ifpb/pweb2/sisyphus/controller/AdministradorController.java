package br.edu.ifpb.pweb2.sisyphus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifpb.pweb2.sisyphus.model.Aluno;
import br.edu.ifpb.pweb2.sisyphus.repository.AlunoRepository;

@Controller
public class AdministradorController {
    
    @Autowired
    private AlunoRepository alunoRepository;

    @RequestMapping("/administrador/home")
    public String showHomepage() {
        return "administrador/home";
    }

    @RequestMapping("administrador/aluno")
    public String showAlunoPage(Model model){
        model.addAttribute("alunos", alunoRepository.findAll());
        return "administrador/aluno/painel";
    }

    @RequestMapping("administrador/cadastrar-aluno")
    public String getFormAluno(Aluno aluno, Model model){
        model.addAttribute("aluno", aluno);
        return "administrador/aluno/form";
    }

    @RequestMapping("/administrador/aluno/save")
    public String saveAluno(Aluno aluno, Model model){
        alunoRepository.save(aluno);
        model.addAttribute("alunos", alunoRepository.findAll());
        return "administrador/aluno/painel";
    }

}
