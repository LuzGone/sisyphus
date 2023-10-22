package br.edu.ifpb.pweb2.sisyphus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifpb.pweb2.sisyphus.repository.AlunoRepository;
import br.edu.ifpb.pweb2.sisyphus.repository.ProcessoRepository;

@Controller
public class AlunoController {
    
    @RequestMapping("/aluno")
    public String showHomePage(){
        return "aluno/home";
    }

    @Autowired
    private AlunoRepository alunoRepository;
    private ProcessoRepository processoRepository;

}
