package br.edu.ifpb.pweb2.sisyphus.controller.Administrador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.sisyphus.model.Aluno;
import br.edu.ifpb.pweb2.sisyphus.model.Curso;
import br.edu.ifpb.pweb2.sisyphus.service.AlunoService;
import br.edu.ifpb.pweb2.sisyphus.service.CursoService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/alunos")
public class AlunosController {
  
    @Autowired
    private AlunoService alunoService;

    @Autowired
    private CursoService cursoService;

    @ModelAttribute("cursos")
    public List<Curso> getListaDeCursos(){
        return this.cursoService.getCursos();
    }

    @GetMapping
    public ModelAndView listarAlunos(ModelAndView model){
        model.addObject("alunos", alunoService.getAlunos());
        model.setViewName("administrador/aluno/painel");
        return model;
    }

    @GetMapping("criar")
    public ModelAndView criarAluno(
        ModelAndView model, 
        RedirectAttributes redirectAttributes 
        ){
        model.addObject("aluno", new Aluno());
        model.addObject("acao", "salvar");
        model.setViewName("administrador/aluno/form");
        return model;
    }

    @PostMapping("criar")
    public ModelAndView salvarAluno(
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
    public ModelAndView editarAluno(
        @PathVariable("id")Long id, 
        ModelAndView model, 
        RedirectAttributes redirectAttributes ){
        model.addObject("aluno", alunoService.getAlunoPorId(id));
        model.addObject("acao", "editar");
        model.setViewName("administrador/aluno/form");
        return model;
    }

    @PostMapping("{id}")
    public ModelAndView atualizarAluno(
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
        redirectAttributes.addFlashAttribute("mensagem", "Aluno foi salvo com sucesso");
        redirectAttributes.addFlashAttribute("alunoSalvo", true); // Se o aluno for salvo com sucesso
        return model;
    }


    @RequestMapping("{id}/delete")
    public ModelAndView deletarAluno(ModelAndView model,@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        this.alunoService.apagarAluno(id);
        model.addObject("alunos", alunoService.getAlunos());
        model.setViewName("redirect:/alunos");
        redirectAttributes.addFlashAttribute("mensagem", "Aluno foi deletado com sucesso.");
        redirectAttributes.addFlashAttribute("alunoDeletado", true);
        return model;
    }

}
