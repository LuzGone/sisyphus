package br.edu.ifpb.pweb2.sisyphus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifpb.pweb2.sisyphus.model.Administrador;
import br.edu.ifpb.pweb2.sisyphus.model.Aluno;
import br.edu.ifpb.pweb2.sisyphus.model.Coordenador;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;
import br.edu.ifpb.pweb2.sisyphus.service.AdministradorService;
import br.edu.ifpb.pweb2.sisyphus.service.AlunoService;
import br.edu.ifpb.pweb2.sisyphus.service.CoordenadorService;
import br.edu.ifpb.pweb2.sisyphus.service.ProfessorService;
import br.edu.ifpb.pweb2.sisyphus.util.PasswordUtil;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/auth")
public class AutenticadorController {
    
    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private CoordenadorService coordenadorService;

    @GetMapping
    public ModelAndView paginaInicialLogin(ModelAndView model){
        model.setViewName("login/login");
        return model;
    }

    //ADMINISTRADOR
    @GetMapping("administrador")
    public ModelAndView loginAdministrador(ModelAndView model){
        model.addObject("administrador", new Administrador());
        model.setViewName("login/login-administrador");
        return model;
    }

    @PostMapping("administrador")
    public ModelAndView validarAdministrador(
        ModelAndView model,
        Administrador administrador,
        HttpSession sessao,
        RedirectAttributes redirectAttributes
        ){
            
        if((administrador = this.isAdmValido(administrador))!=null){
            sessao.setAttribute("administrador", administrador);
            model.setViewName("redirect:/administrador");
            return model;
        }else{
            redirectAttributes.addFlashAttribute("mensagem", "Login e/ou Senha inválido");
            model.setViewName("login/login-administrador");
            return model;
        }
    }

    private Administrador isAdmValido(Administrador administrador){
        Administrador adm = administradorService.getAdministradorPorUsuario(administrador.getUsuario());
        boolean valido = false;
        if(adm!=null){
            if(adm.getSenha().equals(administrador.getSenha())){
                valido = true;
            }
        }
        return valido ? adm : null;
    }

    //COORDENADOR
    @GetMapping("coordenador")
    public ModelAndView loginCoordenador(ModelAndView model){
        model.addObject("coordenador", new Coordenador());
        model.setViewName("login/login-coordenador");
        return model;
    }

    @PostMapping("coordenador")
    public ModelAndView validarCoordenador(
        ModelAndView model,
        Coordenador coordenador,
        HttpSession sessao,
        RedirectAttributes redirectAttributes
        ){
        Professor professor = coordenador.getProfessor();
        if((professor = this.isProfessorValido(professor))!=null){
            if((coordenador = this.isCoordenadorValido(professor)) != null){
                sessao.setAttribute("coordenador", coordenador);
                model.setViewName("redirect:/coordenador/"+coordenador.getId());
                return model;
            }
        }
        redirectAttributes.addFlashAttribute("mensagem", "Login e/ou Senha inválido ou Coordenador não encontrado");
        model.setViewName("login/login-coordenador");
        return model;
    }

    private Coordenador isCoordenadorValido(Professor professor){
        Coordenador coord = coordenadorService.getCoordenadorPorProfessor(professor);
        boolean valido = false;
        if(coord!=null){
            valido = true;
        }
        return valido ? coord : null;
    }

    //PROFESSOR
    @GetMapping("professor")
    public ModelAndView loginProfessor(ModelAndView model){
        model.addObject("professor", new Professor());
        model.addObject("isCoordenador", false);
        model.setViewName("login/login-professor");
        return model;
    }

    @PostMapping("professor")
    public ModelAndView validarProfessor(
        ModelAndView model,
        Professor professor,
        Boolean isCoordenador,
        HttpSession sessao,
        RedirectAttributes redirectAttributes
        ){
        System.out.println(isCoordenador);
        if((professor = this.isProfessorValido(professor))!=null){
            sessao.setAttribute("professor", professor);
            model.setViewName("redirect:/professor/"+professor.getId());
            return model;
        }else{
            redirectAttributes.addFlashAttribute("mensagem", "Login e/ou Senha inválido");
            model.setViewName("login/login-professor");
            return model;
        }
    }

    private Professor isProfessorValido(Professor professor){
        Professor prof = professorService.getProfessorPorUsuario(professor.getUsuario());
        boolean valido = false;
        if(prof!=null){
            if(PasswordUtil.checkPassword(professor.getSenha(), prof.getSenha())){
                valido = true;
            }
        }
        return valido ? prof : null;
    }

    //ALUNO
    @GetMapping("aluno")
    public ModelAndView loginAluno(ModelAndView model){
        model.addObject("aluno", new Aluno());
        model.setViewName("login/login-aluno");
        return model;
    }

    @PostMapping("aluno")
    public ModelAndView validarAluno(
        ModelAndView model,
        Aluno aluno,
        HttpSession sessao,
        RedirectAttributes redirectAttributes
        ){
        if((aluno = this.isAlunoValido(aluno))!=null){
            sessao.setAttribute("aluno", aluno);
            model.setViewName("redirect:/aluno/"+aluno.getId());
            return model;
        }else{
            redirectAttributes.addFlashAttribute("mensagem", "Login e/ou Senha inválido");
            model.setViewName("redirect:/auth/aluno");
            return model;
        }
    }

    private Aluno isAlunoValido(Aluno aluno){
        Aluno alu = alunoService.getAlunoPorUsuario(aluno.getUsuario());
        boolean valido = false;
        if(alu!=null){
            if(PasswordUtil.checkPassword(aluno.getSenha(), alu.getSenha())){
                valido = true;
            }
        }
        return valido ? alu : null;
    }
    
    //Deslogar
    @GetMapping("logout")
    public ModelAndView logout(ModelAndView model, HttpSession sessao){
        sessao.invalidate();
        model.setViewName("redirect:/auth");
        return model;
    }

}
