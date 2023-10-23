package br.edu.ifpb.pweb2.sisyphus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.sisyphus.model.Aluno;
import br.edu.ifpb.pweb2.sisyphus.model.Assunto;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;
import br.edu.ifpb.pweb2.sisyphus.repository.AlunoRepository;
import br.edu.ifpb.pweb2.sisyphus.repository.AssuntoRepository;
import br.edu.ifpb.pweb2.sisyphus.repository.ProfessorRepository;
import br.edu.ifpb.pweb2.sisyphus.service.AlunoService;
import br.edu.ifpb.pweb2.sisyphus.service.AssuntoService;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    
    @RequestMapping("/administrador")
    public String showHomepage() {
        return "administrador/home";
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

}
