package br.edu.ifpb.pweb2.sisyphus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifpb.pweb2.sisyphus.model.Aluno;
import br.edu.ifpb.pweb2.sisyphus.repository.AlunoRepository;
import br.edu.ifpb.pweb2.sisyphus.repository.ProcessoRepository;
import org.springframework.ui.Model;

@Controller
public class AlunoController {
    
    @RequestMapping("/aluno")
    public String showHomePage(){
        return "aluno/home";
    }

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    @RequestMapping("/aluno/{matricula}/processos")
    public String showPainelProcessos(Model model, @PathVariable("matricula")String matricula){
        Aluno aluno = this.alunoRepository.findByMatricula(matricula).get(0);
        model.addAttribute("processos", processoRepository.findByAluno(aluno));
        return "aluno/processo";
    }

}
