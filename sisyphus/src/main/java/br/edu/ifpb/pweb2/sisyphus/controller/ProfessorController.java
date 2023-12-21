package br.edu.ifpb.pweb2.sisyphus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pweb2.sisyphus.model.Colegiado;
import br.edu.ifpb.pweb2.sisyphus.model.Processo;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;
import br.edu.ifpb.pweb2.sisyphus.model.Reuniao;
import br.edu.ifpb.pweb2.sisyphus.model.TipoDecisao;
import br.edu.ifpb.pweb2.sisyphus.service.ProcessoService;
import br.edu.ifpb.pweb2.sisyphus.service.ProfessorService;
import br.edu.ifpb.pweb2.sisyphus.service.ReuniaoService;
import br.edu.ifpb.pweb2.sisyphus.ui.NavPage;
import br.edu.ifpb.pweb2.sisyphus.ui.NavePageBuilder;

@Controller
@RequestMapping("/professor/{id}")
@PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
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

    @ModelAttribute("Deferido")
    public TipoDecisao getDeferido(){
        return TipoDecisao.DEFERIDO;
    }

    @ModelAttribute("Indeferido")
    public TipoDecisao getIndeferido(){
        return TipoDecisao.INDEFERIDO;
    }

    //----- HOME -----
    @GetMapping
    public ModelAndView home(ModelAndView model){
        model.setViewName("/professor/home");
        return model;
    }

    //----- PROCESSOS -----
    @GetMapping("/processos")
    public ModelAndView mostrarPainelDeProcessos(ModelAndView model,
    @PathVariable("id") Long id,
    @RequestParam(defaultValue = "1") int page,
    @RequestParam(defaultValue = "5") int size){
        Professor professor = this.professorService.getProfessorPorId(id);
        Pageable paging = PageRequest.of(page - 1, size);
        Page<Processo> pageProcesso = processoService.getProcessosPorProfessor(professor,paging);
        NavPage navPage = NavePageBuilder.newNavPage(pageProcesso.getNumber() + 1, pageProcesso.getTotalElements(), pageProcesso.getTotalPages(), size);
        model.addObject("navPage", navPage);
        model.addObject("processos", pageProcesso);
        model.setViewName("/professor/painel-processos");
        return model;
    }

    @GetMapping("/processos/{idProcesso}")
    public ModelAndView vizualizarProcesso(ModelAndView model, @PathVariable("idProcesso") Long idProcesso){
        model.addObject("processo", processoService.getProcessoPorId(idProcesso));
        model.setViewName("/professor/processo");
        return model;
    }

    @PostMapping("/processos/{idProcesso}")
    public ModelAndView atribuirDecisaoDoRelatorAoProcesso(ModelAndView model, Processo processo, @PathVariable("id") Long id, @PathVariable("idProcesso") Long idProcesso){
        processoService.atualizarProcesso(processo,idProcesso);
        Professor professor = this.professorService.getProfessorPorId(id);
        model.addObject("processos", processoService.getProcessosPorProfessor(professor));
        model.setViewName("redirect:/professor/"+id+"/processos");
        return model;
    }

    //----- REUNIÕES -----
    @GetMapping("/reunioes")
    public ModelAndView mostrarPainelDeReunioes(ModelAndView model,
    @PathVariable("id") Long id,
    @RequestParam(defaultValue = "1") int page,
    @RequestParam(defaultValue = "5") int size){
        Professor professor = professorService.getProfessorPorId(id);
        Colegiado colegiado = professor.getListaColegiados().get(0);
        Pageable paging = PageRequest.of(page - 1, size);
        Page<Reuniao> pageReunioes = reuniaoService.getReunioesPorColegiado(colegiado,paging);
        NavPage navPage = NavePageBuilder.newNavPage(pageReunioes.getNumber() + 1, pageReunioes.getTotalElements(), pageReunioes.getTotalPages(), size);
        model.addObject("navPage", navPage);
        model.addObject("reunioes", pageReunioes);
        model.setViewName("/professor/painel-reunioes");
        return model;
    }

    @GetMapping("/reunioes/{idReuniao}")
    public ModelAndView mostrarReuniao(ModelAndView model, @PathVariable("idReuniao") Long idReuniao){
        model.addObject("reuniao", this.reuniaoService.getReuniaoPorId(idReuniao));
        model.setViewName("/professor/reuniao");
        return model;
    }

}
