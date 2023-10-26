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

import br.edu.ifpb.pweb2.sisyphus.model.Assunto;
import br.edu.ifpb.pweb2.sisyphus.service.AssuntoService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/assuntos")
public class AssuntosController {
    @Autowired
    private AssuntoService assuntoService;

    @GetMapping
    public ModelAndView listAssuntos(ModelAndView model){
        model.addObject("assuntos", assuntoService.getAssuntos());
        model.addObject("assunto", new Assunto());
        model.setViewName("administrador/assunto/painel");
        return model;
    }

    // @PostMapping
    // public ModelAndView saveAssunto(Assunto assunto, ModelAndView model, RedirectAttributes redirectAttributes){
    //     assuntoService.salvarAssunto(assunto);
    //     model.addObject("assuntos", assuntoService.getAssuntos());
    //     model.addObject("assunto", new Assunto());
    //     model.setViewName("redirect:/assuntos");
    //     redirectAttributes.addFlashAttribute("mensagem", "Assunto Criado com Sucesso");
    //     // Dentro do seu controlador
    //     ((RedirectAttributes) model).addAttribute("assuntoSalvo", true); // Se o assunto for salvo com sucesso
    //     // apagar
    //     return model;
    // }

    // Dentro do seu controlador
    @PostMapping
    public ModelAndView saveAssunto(@Valid Assunto assunto, BindingResult validation, ModelAndView model, RedirectAttributes redirectAttributes){
        if (validation.hasErrors()) {
            model.setViewName("administrador/assunto/form");
            return model;
        }
        assuntoService.salvarAssunto(assunto);
        model.addObject("assuntos", assuntoService.getAssuntos());
        model.addObject("assunto", new Assunto());
        model.setViewName("redirect:/assuntos");
        redirectAttributes.addFlashAttribute("mensagem", "Assunto Criado com Sucesso");
        redirectAttributes.addFlashAttribute("assuntoSalvo", true); 
        return model;
}


    @RequestMapping("{id}/delete")
    public ModelAndView deleteAssunto(@PathVariable("id") Long id, ModelAndView model, RedirectAttributes redirectAttributes){
        assuntoService.deletarAssunto(id);
        model.addObject("assuntos", assuntoService.getAssuntos());
        model.addObject("assunto", new Assunto());
        model.setViewName("redirect:/assuntos");
        redirectAttributes.addFlashAttribute("mensagem", "Assunto Deletado com Sucesso");
        redirectAttributes.addFlashAttribute("assuntoDeletado", true);
        return model;
    }

    @GetMapping("{id}")
    public ModelAndView editAssunto(@PathVariable("id") Long id,ModelAndView model, RedirectAttributes redirectAttributes){
        model.addObject("assunto", assuntoService.getAssuntoPorId(id));
        model.setViewName("administrador/assunto/form");
        redirectAttributes.addFlashAttribute("mensagem", "Assunto Editado com Sucesso");
        redirectAttributes.addFlashAttribute("assuntoEditado", true);
        return model;
    }

    
}
