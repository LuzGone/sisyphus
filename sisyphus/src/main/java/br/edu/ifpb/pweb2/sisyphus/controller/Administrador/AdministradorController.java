package br.edu.ifpb.pweb2.sisyphus.controller.Administrador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    
    @GetMapping
    public ModelAndView mostrarPaginaInicial(ModelAndView model) {
        model.setViewName("administrador/home");
        return model;
    }

}