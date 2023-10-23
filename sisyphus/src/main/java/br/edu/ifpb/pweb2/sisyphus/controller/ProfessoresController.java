package br.edu.ifpb.pweb2.sisyphus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.sisyphus.model.Professor;
import br.edu.ifpb.pweb2.sisyphus.service.ProfessorService;

@Controller
@RequestMapping("/professores")
public class ProfessoresController {
    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public ModelAndView listProfessores(ModelAndView model){
        model.addObject("professores", professorService.getProfessores());
        model.addObject("professor", new Professor());
        model.setViewName("administrador/professor/painel");
        return model;
    }

    @PostMapping
    public ModelAndView saveProfessor(Professor professor, ModelAndView model, RedirectAttributes redirectAttributes){
        professorService.salvarProfessor(professor);
        model.addObject("professores", professorService.getProfessores());
        model.addObject("professor", new Professor());
        model.setViewName("redirect:/professores");
        redirectAttributes.addFlashAttribute("mensagem","Professor Criado com Sucesso");
        return model;
    }

    @RequestMapping("{id}/delete")
    public ModelAndView deleteProfessor(@PathVariable("id") Long id, ModelAndView model, RedirectAttributes redirectAttributes){
        professorService.deletarProfessor(id);
        model.addObject("professores", professorService.getProfessores());
        model.addObject("professor", new Professor());
        model.setViewName("redirect:/professores");
        redirectAttributes.addFlashAttribute("mensagem","Professor Deletado com Sucesso");
        return model;
    }

    @GetMapping("{id}")
    public ModelAndView editProfessor(@PathVariable("id") long id, ModelAndView model){
        model.addObject("professor", professorService.getProfessorPorId(id));
        model.setViewName("administrador/professor/form");
        return model;
    }
}
