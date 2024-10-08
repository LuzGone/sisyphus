package br.edu.ifpb.pweb2.sisyphus.controller.Administrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.sisyphus.model.Assunto;
import br.edu.ifpb.pweb2.sisyphus.service.AssuntoService;
import br.edu.ifpb.pweb2.sisyphus.ui.NavPage;
import br.edu.ifpb.pweb2.sisyphus.ui.NavePageBuilder;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/assuntos")
public class AssuntosController {
    @Autowired
    private AssuntoService assuntoService;

    @PreAuthorize("hasRole('ADMIN')") 
    @GetMapping
    public ModelAndView listarAssuntos(ModelAndView model,
    @RequestParam(defaultValue = "1") int page,
    @RequestParam(defaultValue = "5") int size){
        Pageable paging = PageRequest.of(page - 1, size);
        Page<Assunto> pageAssuntos = assuntoService.getAssuntos(paging);
        NavPage navPage = NavePageBuilder.newNavPage(pageAssuntos.getNumber() + 1, pageAssuntos.getTotalElements(), pageAssuntos.getTotalPages(), size);
        model.addObject("assuntos", pageAssuntos);
        model.addObject("navPage", navPage);
        model.addObject("assunto", new Assunto());
        model.setViewName("administrador/assunto/painel");
        return model;
    }

    @PreAuthorize("hasRole('ADMIN')") 
    @GetMapping("criar")
    public ModelAndView criarAssunto(ModelAndView model, RedirectAttributes redirectAttributes ){
        model.addObject("assunto", new Assunto());
        model.addObject("acao", "salvar");
        model.setViewName("administrador/assunto/form");
        return model;
    }

    @PreAuthorize("hasRole('ADMIN')") 
    @PostMapping("criar")
    public ModelAndView salvarAssunto(
        @Valid Assunto assunto,
        BindingResult validation, 
        ModelAndView model, 
        RedirectAttributes redirectAttributes
        ){
        if (validation.hasErrors()) {
            model.setViewName("administrador/assunto/form");
            model.addObject("assunto", assunto);
            model.addObject("acao", "salvar");
            return model;
        }
        assuntoService.salvarAssunto(assunto);
        model.addObject("assuntos", assuntoService.getAssuntos());
        model.setViewName("redirect:/assuntos");
        redirectAttributes.addFlashAttribute("mensagem", "Assunto foi criado com sucesso");
        redirectAttributes.addFlashAttribute("assuntosSalvo", true);
        return model;
    }

    @PreAuthorize("hasRole('ADMIN')") 
    @GetMapping("{id}")
    public ModelAndView editarAssunto(@PathVariable("id") long id, ModelAndView model, RedirectAttributes redirectAttributes){
        model.addObject("assunto", assuntoService.getAssuntoPorId(id));
        model.addObject("acao", "editar");
        model.setViewName("administrador/assunto/form");
        return model;
    }

    @PreAuthorize("hasRole('ADMIN')") 
    @PostMapping("{id}")
    public ModelAndView atualizarAssunto(
        @Valid Assunto assunto, 
        BindingResult validation,
        @PathVariable("id") Long id,
        ModelAndView model, 
        RedirectAttributes redirectAttributes
        ){
        if (validation.hasErrors()) {
            model.addObject("assunto", assuntoService.getAssuntoPorId(id));
            model.setViewName("redirect:/assuntos/"+id);
            return model;
        }
        assuntoService.salvarAssunto(assunto);
        model.addObject("assuntos", assuntoService.getAssuntos());
        model.setViewName("redirect:/assuntos");
        redirectAttributes.addFlashAttribute("mensagem", "Assunto foi editado com sucesso.");
        redirectAttributes.addFlashAttribute("assuntosEditado", true);
        return model;
    }


    @PreAuthorize("hasRole('ADMIN')") 
    @RequestMapping("{id}/delete")
    public ModelAndView deletarAssunto(@PathVariable("id") Long id, ModelAndView model, RedirectAttributes redirectAttributes){
        assuntoService.deletarAssunto(id);
        model.addObject("assuntos", assuntoService.getAssuntos());
        model.addObject("assunto", new Assunto());
        model.setViewName("redirect:/assuntos");
        redirectAttributes.addFlashAttribute("mensagem", "Assunto foi deletado com sucesso.");
        redirectAttributes.addFlashAttribute("assuntosDeletado", true);
        return model;
    }

    
}
