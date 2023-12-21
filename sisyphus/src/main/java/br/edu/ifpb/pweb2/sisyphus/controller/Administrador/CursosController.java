package br.edu.ifpb.pweb2.sisyphus.controller.Administrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.sisyphus.model.Assunto;
import br.edu.ifpb.pweb2.sisyphus.model.Curso;
import br.edu.ifpb.pweb2.sisyphus.service.CursoService;
import br.edu.ifpb.pweb2.sisyphus.ui.NavPage;
import br.edu.ifpb.pweb2.sisyphus.ui.NavePageBuilder;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/cursos")
public class CursosController {
    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ModelAndView listarCursos(ModelAndView model,
    @RequestParam(defaultValue = "1") int page,
    @RequestParam(defaultValue = "5") int size){
        Pageable paging = PageRequest.of(page - 1, size);
        Page<Curso> pageCursos = cursoService.getCursos(paging);
        NavPage navPage = NavePageBuilder.newNavPage(pageCursos.getNumber() + 1, pageCursos.getTotalElements(), pageCursos.getTotalPages(), size);
        model.addObject("navPage", navPage);
        model.addObject("cursos", pageCursos);
        model.addObject("curso", new Curso());
        model.setViewName("administrador/curso/painel");
        return model;
    }

    @GetMapping("criar")
    public ModelAndView criarCurso(ModelAndView model, RedirectAttributes redirectAttributes ){
        model.addObject("curso", new Curso());
        model.addObject("acao", "salvar");
        model.setViewName("administrador/curso/form");
        return model;
    }

    @PostMapping("criar")
    public ModelAndView salvarCurso(
        @Valid Curso curso,
        BindingResult validation, 
        ModelAndView model, 
        RedirectAttributes redirectAttributes
        ){
        if (validation.hasErrors()) {
            model.setViewName("administrador/curso/form");
            model.addObject("acao", "salvar");
            return model;
        }    
        cursoService.salvarCurso(curso);
        model.addObject("cursos", cursoService.getCursos());
        model.setViewName("redirect:/cursos");
        redirectAttributes.addFlashAttribute("mensagem", "Curso Criado com Sucesso");
        redirectAttributes.addFlashAttribute("cursoSalvo", true);
        return model;
    }

    @GetMapping("{id}")
    public ModelAndView editarCurso(@PathVariable("id") long id, ModelAndView model, RedirectAttributes redirectAttributes){
        model.addObject("curso", cursoService.getCursoPorId(id));
        model.addObject("acao", "editar");
        model.setViewName("administrador/curso/form");
        redirectAttributes.addFlashAttribute("mensagem","Curso Editado com Sucesso");
        redirectAttributes.addFlashAttribute("cursosEditado", true);
        return model;
    }

    @PostMapping("{id}")
    public ModelAndView atualizarCurso(
        @Valid Curso curso, 
        BindingResult validation,
        @PathVariable("id") Long id,
        ModelAndView model, 
        RedirectAttributes redirectAttributes
        ){
        if (validation.hasErrors()) {
            model.addObject("curso", cursoService.getCursoPorId(id));
            model.setViewName("redirect:/cursos/"+id);
            return model;
        }
        cursoService.salvarCurso(curso);
        model.addObject("assuntos", cursoService.getCursos());
        model.setViewName("redirect:/cursos");
        redirectAttributes.addFlashAttribute("mensagem", "Curso Editado com Sucesso");
        redirectAttributes.addFlashAttribute("cursosEditado", true);
        return model;
    }


    @RequestMapping("{id}/delete")
    public ModelAndView deletarCurso(@PathVariable("id") Long id, ModelAndView model, RedirectAttributes redirectAttributes){
        cursoService.deletarCurso(id);
        model.addObject("cursos", cursoService.getCursos());
        model.addObject("curso", new Assunto());
        model.setViewName("redirect:/cursos");
        redirectAttributes.addFlashAttribute("mensagem", "Curso Deletado com Sucesso");
        redirectAttributes.addFlashAttribute("cursosDeletado", true);
        return model;
    }

    
}
