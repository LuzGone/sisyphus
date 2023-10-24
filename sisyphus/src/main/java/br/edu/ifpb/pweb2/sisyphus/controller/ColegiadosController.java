package br.edu.ifpb.pweb2.sisyphus.controller;

import java.util.ArrayList;
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

import br.edu.ifpb.pweb2.sisyphus.model.Colegiado;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;
import br.edu.ifpb.pweb2.sisyphus.service.ColegiadoService;
import br.edu.ifpb.pweb2.sisyphus.service.ProfessorService;

@Controller
@RequestMapping("/colegiados")
public class ColegiadosController {
    
    @Autowired
    private ColegiadoService colegiadoService;

    @Autowired
    private ProfessorService professorService;
    
    @ModelAttribute("professores")
    public List<Professor> getProfessores(){
        return this.professorService.getProfessores();
    }

    @GetMapping
    public ModelAndView listColegiados(ModelAndView model){
        List<Professor> membros = new ArrayList<Professor>();
        for(int i=0 ; i<4;i++){
            membros.add(new Professor());
        }
        model.addObject("colegiados", colegiadoService.getColegiados());
        model.addObject("colegiado", new Colegiado(membros));
        model.addObject("membros", membros);
        model.setViewName("administrador/colegiado/painel");
        return model;
    }

    @PostMapping
    public ModelAndView saveColegiado( 
            Colegiado colegiado,
            ModelAndView model, 
            RedirectAttributes redirectAttributes
        ){
        List<Professor> membros = new ArrayList<Professor>();
        for(int i=0 ; i<4;i++){
            membros.add(new Professor());
        }
        colegiadoService.salvarColegiado(colegiado);
        model.addObject("colegiados", colegiadoService.getColegiados());
        model.addObject("colegiado", new Colegiado(membros));
        model.setViewName("redirect:/colegiados");
        redirectAttributes.addFlashAttribute("mensagem","Colegiado Criado com Sucesso");
        return model;
    }

    @RequestMapping("{id}/delete")
    public ModelAndView deleteColegiado(@PathVariable("id") Long id, ModelAndView model, RedirectAttributes redirectAttributes){
        colegiadoService.deletarColegiado(id);
        model.addObject("colegiados", colegiadoService.getColegiados());
        model.addObject("colegiado", new Colegiado(new ArrayList<Professor>()));
        model.setViewName("redirect:/colegiados");
        redirectAttributes.addFlashAttribute("mensagem","Coordenador Deletado com Sucesso");
        return model;
    }

    @GetMapping("{id}")
    public ModelAndView editCoordenador(@PathVariable("id") long id, ModelAndView model){
        List<Professor> membros = new ArrayList<Professor>();
        for(int i=0 ; i<4;i++){
            membros.add(new Professor());
        }
        model.addObject("membros", membros);
        model.addObject("colegiado", colegiadoService.getColegiadoPorId(id));
        model.setViewName("administrador/colegiado/form");
        return model;
    }
}
