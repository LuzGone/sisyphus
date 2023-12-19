package br.edu.ifpb.pweb2.sisyphus.controller.Administrador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.sisyphus.model.Coordenador;
import br.edu.ifpb.pweb2.sisyphus.model.Curso;
import br.edu.ifpb.pweb2.sisyphus.service.CoordenadorService;
import br.edu.ifpb.pweb2.sisyphus.service.CursoService;
import br.edu.ifpb.pweb2.sisyphus.service.ProfessorService;
import jakarta.validation.Valid;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;


@Controller
@RequestMapping("/coordenadores")
public class CoordenadoresController {
    @Autowired
    private CoordenadorService coordenadorService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private CursoService cursoService;

    @ModelAttribute("professores")
    public List<Professor> getProfessores(){
        return this.professorService.getProfessores();
    }

    @ModelAttribute("cursos")
    public List<Curso> getCursos(){
        return this.cursoService.getCursos();
    }

    @GetMapping
    public ModelAndView listarCoordenadores(ModelAndView model){
        model.addObject("coordenadores", coordenadorService.getCoordenadores());
        model.setViewName("administrador/coordenador/painel");
        return model;
    }

    @GetMapping("criar")
    public ModelAndView criarCoordenador(ModelAndView model, RedirectAttributes redirectAttributes ){
        model.addObject("coordenador", new Coordenador());
        model.addObject("acao", "salvar");
        model.setViewName("administrador/coordenador/form");
        return model;
    }

    @PostMapping("criar")
    public ModelAndView salvarCoordenador(
        @Valid Coordenador coordenador,
        BindingResult validation, 
        ModelAndView model, 
        RedirectAttributes redirectAttributes
        ){
        if (validation.hasErrors()) {
            model.setViewName("administrador/coordenador/form");
            model.addObject("acao", "salvar");
            return model;
        }
        coordenadorService.salvarCoordenador(coordenador);
        model.addObject("coordenadores", professorService.getProfessores());
        model.setViewName("redirect:/coordenadores");
        redirectAttributes.addFlashAttribute("mensagem", "Coordenador Criado com Sucesso");
        redirectAttributes.addFlashAttribute("coordenadoresSalvo", true);
        return model;
    }

    @GetMapping("{id}")
    public ModelAndView editarCoordenador(@PathVariable("id") long id, ModelAndView model){
        model.addObject("coordenador", coordenadorService.getCoordenadorPorId(id));
        model.addObject("acao", "editar");
        model.setViewName("administrador/coordenador/form");
        return model;
    }

    @PostMapping("{id}")
    public ModelAndView atualizarCoordenador(
        @Valid Coordenador coordenador, 
        BindingResult validation,
        @PathVariable("id") Long id,
        ModelAndView model, 
        RedirectAttributes redirectAttributes
        ){
        if (validation.hasErrors()) {
            model.addObject("coordenador", coordenadorService.getCoordenadorPorId(id));
            model.setViewName("redirect:/professores/"+id);
            return model;
        }
        coordenadorService.salvarCoordenador(coordenador);
        model.addObject("coordenadores", coordenadorService.getCoordenadores());
        model.setViewName("redirect:/coordenadores");
        redirectAttributes.addFlashAttribute("mensagem", "Coordenador Editado com Sucesso");
        redirectAttributes.addFlashAttribute("coordenadoresEditado", true);
        return model;
    }

    @RequestMapping("{id}/delete")
    public ModelAndView deletarCoordenador(@PathVariable("id") Long id, ModelAndView model, RedirectAttributes redirectAttributes){
        coordenadorService.deletarCoordenador(id);
        model.addObject("coordenadores", coordenadorService.getCoordenadores());
        model.setViewName("redirect:/coordenadores");
        redirectAttributes.addFlashAttribute("mensagem","Coordenador Deletado com Sucesso");
        redirectAttributes.addFlashAttribute("coordenadoresDeletado", true);
        return model;
    }
}