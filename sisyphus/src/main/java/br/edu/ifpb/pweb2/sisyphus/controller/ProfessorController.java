package br.edu.ifpb.pweb2.sisyphus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pweb2.sisyphus.model.Colegiado;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;
import br.edu.ifpb.pweb2.sisyphus.model.Reuniao;
import br.edu.ifpb.pweb2.sisyphus.service.ProcessoService;
import br.edu.ifpb.pweb2.sisyphus.service.ProfessorService;
import br.edu.ifpb.pweb2.sisyphus.service.ReuniaoService;

@Controller
@RequestMapping("/professor/{id}")
public class ProfessorController {

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private ReuniaoService reuniaoService;

    @ModelAttribute("professor")
    public Professor getProfessor(@PathVariable("id") Long id){
        return this.professorService.getProfessorPorId(id);
    }

    //----- HOME -----
    @GetMapping
    public ModelAndView home(ModelAndView model){
        model.setViewName("/professor/home");
        return model;
    }

    //----- PROCESSOS -----
    @GetMapping("/processos")
    public ModelAndView showPainelProcessos(ModelAndView model,@PathVariable("id") Long id){
        Professor professor = this.professorService.getProfessorPorId(id);
        model.addObject("processos", processoService.getProcessosPorProfessor(professor));
        model.setViewName("/professor/painel-processos");
        return model;
    }

    @GetMapping("/processos/{idProcesso}")
    public ModelAndView showProcesso(ModelAndView model, @PathVariable("idProcesso") Long idProcesso){
        model.addObject("processo", processoService.getProcessoPorId(idProcesso));
        model.setViewName("/professor/processo");
        return model;
    }

    //----- REUNIÕES -----
    @GetMapping("/reunioes")
    public ModelAndView showPainelReunioes(ModelAndView model,@PathVariable("id") Long id){
        Professor professor = professorService.getProfessorPorId(id);
        Colegiado colegiado = professor.getListaColegiados().get(0);
        List<Reuniao> reunioes = colegiado.getReuniaos();
        model.addObject("reunioes", reunioes);
        model.setViewName("/professor/painel-reunioes");
        return model;
    }

    @GetMapping("/reunioes/{idReuniao}")
    public ModelAndView showReuniao(ModelAndView model, @PathVariable("idReuniao") Long idReuniao){
        model.addObject("reuniao", this.reuniaoService.getReuniaoPorId(idReuniao));
        model.setViewName("/professor/reuniao");
        return model;
    }

}
