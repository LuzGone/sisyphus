package br.edu.ifpb.pweb2.sisyphus.controller.Administrador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.sisyphus.model.Curso;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;
import br.edu.ifpb.pweb2.sisyphus.service.CursoService;
import br.edu.ifpb.pweb2.sisyphus.service.ProfessorService;
import br.edu.ifpb.pweb2.sisyphus.ui.NavPage;
import br.edu.ifpb.pweb2.sisyphus.ui.NavePageBuilder;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/professores")
public class ProfessoresController {
    @Autowired
    private ProfessorService professorService;

    @Autowired
    private CursoService cursoService;

    @ModelAttribute("cursos")
    public List<Curso> getListaDeCursos(){
        return this.cursoService.getCursos();
    }

    @PreAuthorize("hasRole('ADMIN')") 
    @GetMapping
    public ModelAndView listarProfessores(ModelAndView model,
    @RequestParam(defaultValue = "1") int page,
    @RequestParam(defaultValue = "5") int size){
        Pageable paging = PageRequest.of(page - 1, size);
        Page<Professor> pageProfessores = professorService.getProfessores(paging);
        NavPage navPage = NavePageBuilder.newNavPage(pageProfessores.getNumber() + 1, pageProfessores.getTotalElements(), pageProfessores.getTotalPages(), size);
        model.addObject("navPage", navPage);
        model.addObject("professores", pageProfessores);
        model.addObject("professor", new Professor());
        model.setViewName("administrador/professor/painel");
        return model;
    }

    @PreAuthorize("hasRole('ADMIN')") 
    @GetMapping("criar")
    public ModelAndView criarProfessor(ModelAndView model, RedirectAttributes redirectAttributes ){
        model.addObject("professor", new Professor());
        model.addObject("cursos", this.cursoService.getCursos());
        model.addObject("acao", "salvar");
        model.setViewName("administrador/professor/form");
        return model;
    }

    @PreAuthorize("hasRole('ADMIN')") 
    @PostMapping("criar")
    public ModelAndView salvarProfessor(
        @Valid Professor professor,
        BindingResult validation, 
        ModelAndView model, 
        RedirectAttributes redirectAttributes
        ){
        if (validation.hasErrors()) {
            model.setViewName("administrador/professor/form");
            model.addObject("acao", "salvar");
            return model;
        }    
        professorService.salvarProfessor(professor);
        model.addObject("professores", professorService.getProfessores());
        model.setViewName("redirect:/professores");
        redirectAttributes.addFlashAttribute("mensagem", "Professor Criado com Sucesso");
        redirectAttributes.addFlashAttribute("professoresSalvo", true);
        return model;
    }

    @PreAuthorize("hasRole('ADMIN')") 
    @GetMapping("{id}")
    public ModelAndView editarProfessor(@PathVariable("id") long id, ModelAndView model, RedirectAttributes redirectAttributes){
        model.addObject("professor", professorService.getProfessorPorId(id));
        model.addObject("acao", "editar");
        model.addObject("cursos", this.cursoService.getCursos());
        model.setViewName("administrador/professor/form");
        return model;
    }

    @PreAuthorize("hasRole('ADMIN')") 
    @PostMapping("{id}")
    public ModelAndView atualizarProfessor(
        @Valid Professor professor, 
        BindingResult validation,
        @PathVariable("id") Long id,
        ModelAndView model, 
        RedirectAttributes redirectAttributes
        ){
        if (validation.hasErrors()) {
            model.addObject("professor", professorService.getProfessorPorId(id));
            model.setViewName("redirect:/professores/"+id);
            return model;
        }
        professorService.salvarProfessor(professor);
        model.addObject("professores", professorService.getProfessores());
        model.setViewName("redirect:/professores");
        redirectAttributes.addFlashAttribute("mensagem", "Professor Editado com Sucesso");
        redirectAttributes.addFlashAttribute("professorEditado", true);
        return model;
    }

    @PreAuthorize("hasRole('ADMIN')") 
    @RequestMapping("{id}/delete")
    public ModelAndView deletarProfessor(@PathVariable("id") Long id, ModelAndView model, RedirectAttributes redirectAttributes){
        professorService.deletarProfessor(id);
        model.addObject("professores", professorService.getProfessores());
        model.addObject("professor", new Professor());
        model.setViewName("redirect:/professores");
        redirectAttributes.addFlashAttribute("mensagem","Professor Deletado com Sucesso");
        redirectAttributes.addFlashAttribute("professoresDeletado", true);
        return model;
    }
    
}
