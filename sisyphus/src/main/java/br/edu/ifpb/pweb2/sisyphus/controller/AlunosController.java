package br.edu.ifpb.pweb2.sisyphus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.sisyphus.model.Aluno;
import br.edu.ifpb.pweb2.sisyphus.service.AlunoService;

@Controller
@RequestMapping("/alunos")
public class AlunosController {
    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ModelAndView listAlunos(ModelAndView model){
        //Primeiro Adicionamos os objetos que vamos acessar nas paginas atraves do model
        model.addObject("alunos", alunoService.getAlunos());
        model.addObject("aluno", new Aluno());
        //Depois Dizemos o caminho da view
        model.setViewName("administrador/aluno/painel");
        return model;
    }

    @PostMapping
    public ModelAndView saveAluno(Aluno aluno, ModelAndView model, RedirectAttributes redirectAttributes){
        alunoService.salvarAluno(aluno);
        model.addObject("alunos", alunoService.getAlunos());
        model.addObject("aluno", new Aluno());
        model.setViewName("redirect:/alunos");
        redirectAttributes.addFlashAttribute("mensagem", "Aluno Criado com Sucesso");
        return model;
    }

    @RequestMapping("{id}/delete")
    public ModelAndView deleteAluno(ModelAndView model,@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        this.alunoService.apagarAluno(id);
        model.addObject("alunos", alunoService.getAlunos());
        model.addObject("aluno", new Aluno());
        model.setViewName("redirect:/alunos");
        redirectAttributes.addFlashAttribute("mensagem", "Aluno Deletado com Sucesso");
        return model;
    }

    @GetMapping("{id}")
    public ModelAndView editAluno(@PathVariable("id")Long id, ModelAndView model){
        model.addObject("aluno", alunoService.getAlunoPorId(id));
        model.setViewName("administrador/aluno/form");
        return model;
    }
}
