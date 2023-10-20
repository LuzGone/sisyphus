package br.edu.ifpb.pweb2.sisyphus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdministradorController {
    @RequestMapping("/administrador/home")
    public String showHomepage() {
        return "administrador/home";
    }

    @RequestMapping("administrador/aluno")
    public String showAlunoPage(){
        return "administrador/aluno";
    }
}
