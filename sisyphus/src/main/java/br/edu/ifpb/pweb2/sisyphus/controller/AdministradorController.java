package br.edu.ifpb.pweb2.sisyphus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifpb.pweb2.sisyphus.model.Aluno;
import br.edu.ifpb.pweb2.sisyphus.model.Assunto;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;
import br.edu.ifpb.pweb2.sisyphus.repository.AlunoRepository;
import br.edu.ifpb.pweb2.sisyphus.repository.AssuntoRepository;
import br.edu.ifpb.pweb2.sisyphus.repository.ProfessorRepository;

@Controller
public class AdministradorController {
    
    @RequestMapping("/administrador")
    public String showHomepage() {
        return "administrador/home";
    }

    //------ ALUNO --------------//
    
    @Autowired
    private AlunoRepository alunoRepository;

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

    //------ PROFESSOR --------------//

    @Autowired
    private ProfessorRepository professorRepository;

    @RequestMapping("administrador/professor")
    public String showProfessorPage(Model model){
        model.addAttribute("professores", professorRepository.findAll());
        return "administrador/professor/painel";
    }

    @RequestMapping("administrador/cadastrar-professor")
    public String getFormProfessor(Professor professor, Model model){
        model.addAttribute("professor", professor);
        return "administrador/professor/form";
    }

    @RequestMapping("/administrador/professor/save")
    public String saveProfessor(Professor professor, Model model){
        professorRepository.save(professor);
        model.addAttribute("professores", professorRepository.findAll());
        return "administrador/professor/painel";
    }

    //------ ASSUNTO ---------//
    @Autowired
    private AssuntoRepository assuntoRepository;

    @RequestMapping("administrador/assunto")
    public String showAssuntoPage(Model model){
        model.addAttribute("assuntos", assuntoRepository.findAll());
        return "administrador/assunto/painel";
    }

    @RequestMapping("administrador/cadastrar-assunto")
    public String getFormAssunto(Assunto assunto, Model model){
        model.addAttribute("assunto", assunto);
        return "administrador/assunto/form";
    }

    @RequestMapping("/administrador/assunto/save")
    public String saveAssunto(Assunto assunto, Model model){
        assuntoRepository.save(assunto);
        model.addAttribute("assuntos", assuntoRepository.findAll());
        return "administrador/assunto/painel";
    }


}
