package br.edu.ifpb.pweb2.sisyphus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/auth")
public class AutenticadorController {

    @GetMapping("/login")
    public ModelAndView paginaInicialLogin(ModelAndView model){
        model.setViewName("login/login");
        return model;
    }

}
