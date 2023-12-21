package br.edu.ifpb.pweb2.sisyphus.controller;

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

import br.edu.ifpb.pweb2.sisyphus.model.Aluno;
import br.edu.ifpb.pweb2.sisyphus.model.Assunto;
import br.edu.ifpb.pweb2.sisyphus.model.Processo;
import br.edu.ifpb.pweb2.sisyphus.service.AlunoService;
import br.edu.ifpb.pweb2.sisyphus.service.AssuntoService;
import br.edu.ifpb.pweb2.sisyphus.service.ProcessoService;
import br.edu.ifpb.pweb2.sisyphus.ui.NavPage;
import br.edu.ifpb.pweb2.sisyphus.ui.NavePageBuilder;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/aluno/{id}")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private AssuntoService assuntoService;

    @ModelAttribute("assuntos")
    public List<Assunto> getAssuntos(){
        return this.assuntoService.getAssuntos();
    }
   
    @GetMapping
    public ModelAndView paginaInicial(ModelAndView model, @PathVariable("id")Long id){
        Aluno aluno = this.alunoService.getAlunoPorId(id);
        model.addObject("aluno", aluno);
        model.setViewName("/aluno/home");
        return model;
    }


    @GetMapping("/processos")
    public ModelAndView listarProcessos(ModelAndView model, 
    @PathVariable("id")Long id,
    @RequestParam(defaultValue = "1") int page,
    @RequestParam(defaultValue = "5") int size){
        Aluno aluno = this.alunoService.getAlunoPorId(id);
        Pageable paging = PageRequest.of(page - 1, size);
        Page<Processo> pageProcesso = processoService.getProcessosPorAluno(aluno,paging);
        NavPage navPage = NavePageBuilder.newNavPage(pageProcesso.getNumber() + 1, pageProcesso.getTotalElements(), pageProcesso.getTotalPages(), size);
        model.addObject("navPage", navPage);
        model.addObject("aluno", aluno);
        model.addObject("processos", processoService.getProcessosPorAluno(aluno));
        model.setViewName("/aluno/painel-processos");
        return model;
    }

    @GetMapping("/processos/criar")
    public ModelAndView criarProcesso(ModelAndView model,@PathVariable("id")Long id, RedirectAttributes redirectAttributes ){
        Aluno aluno = this.alunoService.getAlunoPorId(id);
        model.addObject("aluno", aluno);
        model.addObject("processo", new Processo(aluno,new Assunto()));
        model.setViewName("/aluno/criar-processo");
        return model;
    }

    @PostMapping("/processos/criar")
    public ModelAndView salvarProcesso(
        @Valid Processo processo,
        BindingResult validation, 
        @PathVariable("id")Long id,
        ModelAndView model, 
        RedirectAttributes redirectAttributes
        ){
        Aluno aluno = this.alunoService.getAlunoPorId(id);
        if (validation.hasErrors()) {
            model.addObject("aluno", aluno);
            model.addObject("processo", new Processo(aluno,new Assunto()));
            model.setViewName("/aluno/criar-processo");
            return model;
        }  
        processo.setAluno(aluno);    
        processoService.salvarProcesso(processo);
        model.addObject("aluno", aluno);
        model.addObject("processos", processoService.getProcessosPorAluno(aluno));
        model.setViewName("redirect:/aluno/"+id+"/processos");
        redirectAttributes.addFlashAttribute("mensagem", "Processo criado com Sucesso");
        return model;
    }

    @GetMapping("processos/{idProcesso}")
    public ModelAndView vizualizarProcesso(ModelAndView model, @PathVariable("id") Long id,@PathVariable("idProcesso") Long idProcesso){
        model.addObject("processo", processoService.getProcessoPorId(idProcesso));
        model.addObject("aluno", alunoService.getAlunoPorId(id));
        model.setViewName("/aluno/processo");
        return model;
    }

}
