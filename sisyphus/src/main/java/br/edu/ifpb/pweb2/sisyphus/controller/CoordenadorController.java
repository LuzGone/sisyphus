package br.edu.ifpb.pweb2.sisyphus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.sisyphus.service.ProcessoService;
import br.edu.ifpb.pweb2.sisyphus.service.ProfessorService;
import java.util.List;

import br.edu.ifpb.pweb2.sisyphus.model.Processo;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;

@Controller
@RequestMapping("/coordenador/processos")
public class CoordenadorController {

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private ProfessorService professorService;

    @ModelAttribute("professores")
    public List<Professor> getProfessores(){
        return this.professorService.getProfessoresComColegiado();
    }

    @GetMapping
    public ModelAndView showPainelProcessos(ModelAndView model){
        model.addObject("processos", processoService.getProcessos());
        model.setViewName("/coordenador/processo");
        return model;
    }

    @GetMapping("{id}")
    public ModelAndView showProcesso(ModelAndView model, @PathVariable("id") Long id){
        model.addObject("processo", processoService.getProcessoPorId(id));
        model.setViewName("/coordenador/processo");
        return model;
    }

    @PostMapping("{id}")
    public ModelAndView salvarProcesso(
        ModelAndView model,
        Processo processo,
        @PathVariable("id")Long id,
        RedirectAttributes redirectAttributes
    ){    
            processoService.salvarProcesso(processo);
            model.addObject("processos", processoService.getProcessos());
            model.setViewName("redirect:/coordenador/processos");
            redirectAttributes.addFlashAttribute("mensagem", "Processo designado com Sucesso");
            return model;
    }

}
