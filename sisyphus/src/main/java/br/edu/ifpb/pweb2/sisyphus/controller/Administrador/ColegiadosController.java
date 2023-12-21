package br.edu.ifpb.pweb2.sisyphus.controller.Administrador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import br.edu.ifpb.pweb2.sisyphus.model.Colegiado;
import br.edu.ifpb.pweb2.sisyphus.model.Coordenador;
import br.edu.ifpb.pweb2.sisyphus.model.Curso;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;
import br.edu.ifpb.pweb2.sisyphus.service.ColegiadoService;
import br.edu.ifpb.pweb2.sisyphus.service.CoordenadorService;
import br.edu.ifpb.pweb2.sisyphus.service.CursoService;
import br.edu.ifpb.pweb2.sisyphus.service.ProfessorService;
import br.edu.ifpb.pweb2.sisyphus.ui.NavPage;
import br.edu.ifpb.pweb2.sisyphus.ui.NavePageBuilder;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/colegiados")
public class ColegiadosController {
    
    @Autowired
    private ColegiadoService colegiadoService;

    @Autowired
    private CoordenadorService coordenadorService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private CursoService cursoService;
    
    //ATRIBUTOS
    @ModelAttribute("professores")
    public List<Professor> getProfessores(){
        return this.professorService.getProfessores();
    }

    @ModelAttribute("cursos")
    public List<Curso> getCursos(){
        return this.cursoService.getCursos();
    }

    @ModelAttribute("coordenadores")
    public List<Coordenador> getCoordenadores(){
        return this.coordenadorService.getCoordenadores();
    }

    //GETS E POSTS
    @GetMapping
    public ModelAndView listarColegiados(ModelAndView model,
    @RequestParam(defaultValue = "1") int page,
    @RequestParam(defaultValue = "5") int size){
        Pageable paging = PageRequest.of(page - 1, size);
        Page<Colegiado> pageColegiados = colegiadoService.getColegiados(paging);
        NavPage navPage = NavePageBuilder.newNavPage(pageColegiados.getNumber() + 1, pageColegiados.getTotalElements(), pageColegiados.getTotalPages(), size);
        model.addObject("navPage", navPage);
        model.addObject("colegiados", pageColegiados);
        model.setViewName("administrador/colegiado/painel");
        return model;
    }

    @GetMapping("criar")
    public ModelAndView criarColegiado(ModelAndView model, RedirectAttributes redirectAttributes ){
        List<Professor> membros = new ArrayList<Professor>();
        for(int i=0 ; i<4;i++){
            membros.add(new Professor());
        }
        model.addObject("colegiado", new Colegiado(membros));
        model.addObject("membros", membros);
        model.addObject("acao", "salvar");
        model.setViewName("administrador/colegiado/form");
        return model;
    }

    @PostMapping("criar")
    public ModelAndView salvarColegiado(
        @Valid Colegiado colegiado,
        BindingResult validation, 
        ModelAndView model, 
        RedirectAttributes redirectAttributes
        ){
        if (validation.hasErrors()) {
            List<Professor> membros = new ArrayList<Professor>();
            for(int i=0 ; i<4;i++){
                membros.add(new Professor());
            }
            model.addObject("membros", membros);
            model.setViewName("administrador/colegiado/form");
            model.addObject("acao", "salvar");
            return model;
        }    
        colegiadoService.salvarColegiado(colegiado);
        model.addObject("colegiados", colegiadoService.getColegiados());
        model.setViewName("redirect:/colegiados");
        redirectAttributes.addFlashAttribute("mensagem", "Colegiado Criado com Sucesso");
        redirectAttributes.addFlashAttribute("colegiadosSalvo", true);
        return model;
    }

    @GetMapping("{id}")
    public ModelAndView editarColegiado(@PathVariable("id") long id, ModelAndView model, RedirectAttributes redirectAttributes){
        List<Professor> membros = new ArrayList<Professor>();
        for(int i=0 ; i<4;i++){
            membros.add(new Professor());
        }
        model.addObject("membros", membros);
        model.addObject("colegiado", colegiadoService.getColegiadoPorId(id));
        model.addObject("acao", "editar");
        model.setViewName("administrador/colegiado/form");
        return model;
    }

    @PostMapping("{id}")
    public ModelAndView atualizarColegiado(
        @Valid Colegiado colegiado, 
        BindingResult validation,
        @PathVariable("id") Long id,
        ModelAndView model, 
        RedirectAttributes redirectAttributes
        ){
        if (validation.hasErrors()) {
            List<Professor> membros = new ArrayList<Professor>();
            for(int i=0 ; i<4;i++){
                membros.add(new Professor());
            }
            model.addObject("membros", membros);
            model.addObject("colegiado", colegiadoService.getColegiadoPorId(id));
            model.setViewName("redirect:/colegiados/"+id);
            return model;
        }
        colegiadoService.salvarColegiado(colegiado);
        model.addObject("colegiado", colegiadoService.getColegiadoPorId(id));
        model.setViewName("redirect:/colegiados");
        redirectAttributes.addFlashAttribute("mensagem", "Colegiado Editado com Sucesso");
        redirectAttributes.addFlashAttribute("colegiadosEditado", true);
        return model;
    }


    @RequestMapping("{id}/delete")
    public ModelAndView deletarColegiado(@PathVariable("id") Long id, ModelAndView model, RedirectAttributes redirectAttributes){
        colegiadoService.deletarColegiado(id);
        model.addObject("colegiados", colegiadoService.getColegiados());
        model.addObject("colegiado", new Colegiado(new ArrayList<Professor>()));
        model.setViewName("redirect:/colegiados");
        redirectAttributes.addFlashAttribute("mensagem","Colegiado Deletado com Sucesso");
        redirectAttributes.addFlashAttribute("colegiadoDeletado", true);
        return model;
    }
}
