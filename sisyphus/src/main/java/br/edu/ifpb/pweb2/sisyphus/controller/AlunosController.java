package br.edu.ifpb.pweb2.sisyphus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.sisyphus.model.Aluno;
import br.edu.ifpb.pweb2.sisyphus.service.AlunoService;
import jakarta.validation.Valid;




@Controller
@RequestMapping("/alunos")
public class AlunosController {
  
    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ModelAndView listAlunos(ModelAndView model){
        //Primeiro Adicionamos os objetos que vamos acessar nas paginas atraves do model
        model.addObject("alunos", alunoService.getAlunos());
        //Depois Dizemos o caminho da view
        model.setViewName("administrador/aluno/painel");
        return model;
    }

    @GetMapping("criar")
    public ModelAndView createAluno(ModelAndView model, RedirectAttributes redirectAttributes ){
        model.addObject("aluno", new Aluno());
        model.addObject("acao", "salvar");
        model.setViewName("administrador/aluno/form");
        return model;
    }

    @PostMapping("criar")
    public ModelAndView saveAluno(
        @Valid Aluno aluno,
        BindingResult validation, 
        ModelAndView model, 
        RedirectAttributes redirectAttributes
        ){
        if (validation.hasErrors()) {
            model.setViewName("administrador/aluno/form");
            return model;
        }    
        alunoService.salvarAluno(aluno);
        model.addObject("alunos", alunoService.getAlunos());
        model.setViewName("redirect:/alunos");
        redirectAttributes.addFlashAttribute("mensagem", "Aluno Criado com Sucesso");
        return model;
    }

    @GetMapping("{id}")
    public ModelAndView editAluno(@PathVariable("id")Long id, ModelAndView model, RedirectAttributes redirectAttributes ){
        model.addObject("aluno", alunoService.getAlunoPorId(id));
        model.addObject("acao", "editar");
        model.setViewName("administrador/aluno/form");
        redirectAttributes.addFlashAttribute("mensagem", "Aluno Editado com Sucesso");
        redirectAttributes.addFlashAttribute("alunoEditado", true);
        return model;
    }

    @PostMapping("{id}")
    public ModelAndView updateAluno(
        @Valid Aluno aluno, 
        BindingResult validation,
        @PathVariable("id") Long id,
        ModelAndView model, 
        RedirectAttributes redirectAttributes
        ){
        if (validation.hasErrors()) {
            model.addObject("aluno", alunoService.getAlunoPorId(id));
            model.setViewName("redirect:/alunos/"+id);
            return model;
        }
        alunoService.salvarAluno(aluno);
        model.addObject("alunos", alunoService.getAlunos());
        model.setViewName("redirect:/alunos");
        redirectAttributes.addFlashAttribute("mensagem", "Aluno Salvo com Sucesso");
        redirectAttributes.addFlashAttribute("alunoSalvo", true); // Se o aluno for salvo com sucesso
        return model;
    }


    @RequestMapping("{id}/delete")
    public ModelAndView deleteAluno(ModelAndView model,@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        this.alunoService.apagarAluno(id);
        model.addObject("alunos", alunoService.getAlunos());
        model.setViewName("redirect:/alunos");
        redirectAttributes.addFlashAttribute("mensagem", "Aluno Deletado com Sucesso");
        redirectAttributes.addFlashAttribute("alunoDeletado", true);
        return model;
    }

}
