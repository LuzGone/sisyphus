package br.edu.ifpb.pweb2.sisyphus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    
    @RequestMapping("/administrador")
    public String showHomepage() {
        return "administrador/home";
    }

    //------ PROFESSOR --------------//

}
