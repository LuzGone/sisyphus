package br.edu.ifpb.pweb2.sisyphus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifpb.pweb2.sisyphus.model.Administrador;
import br.edu.ifpb.pweb2.sisyphus.model.Aluno;
import br.edu.ifpb.pweb2.sisyphus.model.Coordenador;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;
import br.edu.ifpb.pweb2.sisyphus.service.AdministradorService;
import br.edu.ifpb.pweb2.sisyphus.service.AlunoService;
import br.edu.ifpb.pweb2.sisyphus.service.ProfessorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/auth")
public class AutenticadorController {
    
    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AdministradorService administradorService;

    @GetMapping
    public ModelAndView paginaInicialLogin(ModelAndView model){
        model.setViewName("login/login");
        return model;
    }

    //ADMINISTRADOR
    @GetMapping("administrador")
    public ModelAndView loginAdministrador(ModelAndView model){
        model.addObject("administrador", new Administrador());
        model.setViewName("login/login-administrador");
        return model;
    }

    //PROFESSOR
    @GetMapping("professor")
    public ModelAndView loginProfessor(ModelAndView model){
        model.addObject("professor", new Professor());
        model.setViewName("login/login-professor");
        return model;
    }

    //ALUNO
    @GetMapping("aluno")
    public ModelAndView loginAluno(ModelAndView model){
        model.addObject("aluno", new Aluno());
        model.setViewName("login/login-aluno");
        return model;
    }
    
    


}
