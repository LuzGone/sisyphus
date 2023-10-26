package br.edu.ifpb.pweb2.sisyphus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.sisyphus.model.Coordenador;
import br.edu.ifpb.pweb2.sisyphus.service.CoordenadorService;
import br.edu.ifpb.pweb2.sisyphus.service.ProfessorService;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;


@Controller
@RequestMapping("/coordenadores")
public class CoordenadoresController {
    @Autowired
    private CoordenadorService coordenadorService;

    @Autowired
    private ProfessorService professorService;

    @ModelAttribute("professores")
    public List<Professor> getProfessores(){
        return this.professorService.getProfessores();
    }

    @GetMapping
    public ModelAndView listCoordenadores(ModelAndView model){
        model.addObject("coordenadores", coordenadorService.getCoordenadores());
        model.addObject("coordenador", new Coordenador(new Professor(), ""));
        model.setViewName("administrador/coordenador/painel");
        return model;
    }

    @PostMapping
    public ModelAndView saveCoordenador( 
            Coordenador coordenador,
            ModelAndView model, 
            RedirectAttributes redirectAttributes
        ){
        coordenadorService.salvarCoordenador(coordenador);
        model.addObject("coordenadores", coordenadorService.getCoordenadores());
        model.addObject("coordenador", new Coordenador(new Professor(), ""));
        model.setViewName("redirect:/coordenadores");
        redirectAttributes.addFlashAttribute("mensagem","Coordenador Criado com Sucesso");
        redirectAttributes.addFlashAttribute("coordenadoresSalvo", true);
        return model;
    }

    @RequestMapping("{id}/delete")
    public ModelAndView deleteCoordenador(@PathVariable("id") Long id, ModelAndView model, RedirectAttributes redirectAttributes){
        coordenadorService.deletarCoordenador(id);
        model.addObject("coordenadores", coordenadorService.getCoordenadores());
        model.addObject("coordenador", new Coordenador(new Professor(), ""));
        model.setViewName("redirect:/coordenadores");
        redirectAttributes.addFlashAttribute("mensagem","Coordenador Deletado com Sucesso");
        redirectAttributes.addFlashAttribute("coordenadoresDeletado", true);
        return model;
    }

    @GetMapping("{id}")
    public ModelAndView editCoordenador(@PathVariable("id") long id, ModelAndView model, RedirectAttributes redirectAttributes){
        model.addObject("coordenador", coordenadorService.getCoordenadorPorId(id));
        model.setViewName("administrador/coordenador/form");
        redirectAttributes.addFlashAttribute("mensagem","Coordenador Editado com Sucesso");
        redirectAttributes.addFlashAttribute("coordenadoresEditado", true);
        return model;
    }
}
