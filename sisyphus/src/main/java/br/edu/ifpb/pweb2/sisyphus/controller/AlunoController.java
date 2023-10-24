package br.edu.ifpb.pweb2.sisyphus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifpb.pweb2.sisyphus.model.Aluno;
import br.edu.ifpb.pweb2.sisyphus.model.Assunto;
import br.edu.ifpb.pweb2.sisyphus.model.Colegiado;
import br.edu.ifpb.pweb2.sisyphus.model.Processo;
import br.edu.ifpb.pweb2.sisyphus.service.AlunoService;
import br.edu.ifpb.pweb2.sisyphus.service.AssuntoService;
import br.edu.ifpb.pweb2.sisyphus.service.ProcessoService;

import org.springframework.ui.Model;

@Controller
public class AlunoController {
    
    @RequestMapping("/aluno")
    public String showHomePage(){
        return "aluno/home";
    }

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private AssuntoService assuntoService;

    @RequestMapping("/aluno/{id}/processos")
    public String showPainelProcessos(Model model, @PathVariable("id")Long id, Processo processo){
        Aluno aluno = this.alunoService.getAlunoPorId(id);
        model.addAttribute("aluno", aluno);
        processo.setAluno(aluno);
        model.addAttribute("processo", processo);
        model.addAttribute("processos", processoService.getProcessosPorAluno(aluno));
        model.addAttribute("assuntos", assuntoService.getAssuntos());
        return "aluno/processo";
    }

    @RequestMapping("/aluno/{id}/processo/save")
    public String salvarProcesso(
        Model model, 
        @PathVariable("id")Long id, 
        @RequestParam("assunto") Long assuntoId,
        @RequestParam("textoRequerimento")String textoRequerimento
        ){
            Aluno aluno = this.alunoService.getAlunoPorId(id);
            Assunto assunto = this.assuntoService.getAssuntoPorId(assuntoId);
            Processo processo = new Processo(aluno, assunto, textoRequerimento,new Colegiado());
            aluno.adicionarProcesso(processo);
            processoService.salvarProcesso(processo);
            alunoService.salvarAluno(aluno);
            model.addAttribute("aluno", aluno);

            Processo novoProcesso = new Processo();
            novoProcesso.setAluno(aluno);
            model.addAttribute("processo", novoProcesso);
            model.addAttribute("processos", processoService.getProcessosPorAluno(aluno));
            model.addAttribute("assuntos", assuntoService.getAssuntos());
            return "aluno/processo";
    }

}
