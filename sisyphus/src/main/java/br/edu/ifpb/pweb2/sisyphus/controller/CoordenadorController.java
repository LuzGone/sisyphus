package br.edu.ifpb.pweb2.sisyphus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.sisyphus.service.AlunoService;
import br.edu.ifpb.pweb2.sisyphus.service.ColegiadoService;
import br.edu.ifpb.pweb2.sisyphus.service.CoordenadorService;
import br.edu.ifpb.pweb2.sisyphus.service.ProcessoService;
import br.edu.ifpb.pweb2.sisyphus.service.ProfessorService;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.pweb2.sisyphus.model.Aluno;
import br.edu.ifpb.pweb2.sisyphus.model.Colegiado;
import br.edu.ifpb.pweb2.sisyphus.model.Coordenador;
import br.edu.ifpb.pweb2.sisyphus.model.Processo;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;
import br.edu.ifpb.pweb2.sisyphus.model.Reuniao;

@Controller
@RequestMapping("/coordenador/{id}")
public class CoordenadorController {

    @Autowired
    private CoordenadorService coordenadorService;

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ColegiadoService colegiadoService;

    @ModelAttribute("coordenador")
    public Coordenador getCoordenador(@PathVariable("id") Long id){
        return this.coordenadorService.getCoordenadorPorId(id);
    }

    @ModelAttribute("relatores")
    public List<Professor> getRelatores(){
        return this.professorService.getProfessoresComProcessos();
    }

    @ModelAttribute("professores")
    public List<Professor> getProfessores(){
        return this.professorService.getProfessoresComColegiado();
    }

    @ModelAttribute("alunos")
    public List<Aluno> getAlunos(){
        return this.alunoService.getAlunosComProcessos();
    }

    //------ PROCESSOS ---------

    @GetMapping("processos")
    public ModelAndView showPainelProcessos(ModelAndView model){
        model.addObject("processos", processoService.getProcessos());
        model.setViewName("/coordenador/painel");
        return model;
    }

    @GetMapping("processos/{idProcesso}")
    public ModelAndView showProcesso(ModelAndView model, @PathVariable("idProcesso") Long id){
        model.addObject("processo", processoService.getProcessoPorId(id));
        model.setViewName("/coordenador/processo");
        return model;
    }

    @PostMapping("processos/{idProcesso}")
    public ModelAndView salvarProcesso(
        ModelAndView model,
        Processo processo,
        @PathVariable("id")Long id,
        @PathVariable("idProcesso")Long idProcesso,
        RedirectAttributes redirectAttributes
    ){ 
            processoService.atribuirProcesso(processo,idProcesso);
            model.addObject("processos", processoService.getProcessos());
            model.setViewName("redirect:/coordenador/"+id+"/processos");
            redirectAttributes.addFlashAttribute("mensagem", "Processo designado com Sucesso");
            return model;
    }

    //------ REUNIÕES ---------

    @GetMapping("reunioes")
    public ModelAndView showPainelReuniaos(ModelAndView model, @PathVariable("id") Long id){
        Coordenador coordenador = coordenadorService.getCoordenadorPorId(id);
        Colegiado colegiado = colegiadoService.getColegiadoPorCoordenador(coordenador);
        model.addObject("reuniaos", colegiado.getReuniaos());
        model.setViewName("/coordenador/painel");
        return model;
    }

    @GetMapping("reuniaos/criar")
    public ModelAndView createReuniao(ModelAndView model,@PathVariable("id")Long id){
        List<Processo> processosDisponiveis = new ArrayList<Processo>();
        Coordenador coordenador = coordenadorService.getCoordenadorPorId(id);
        Colegiado colegiado = colegiadoService.getColegiadoPorCoordenador(coordenador);

        for(Processo processo : colegiado.getProcessos()){
            if(processo.getRelator()!= null){
                processosDisponiveis.add(processo);
            }
        }

        List<Processo> processosEscolhidos = new ArrayList<Processo>();
        Reuniao reuniao = new Reuniao();
        model.addObject("processosEscolhidos", processosEscolhidos);
        model.addObject("processosDisponiveis", processosDisponiveis);
        model.addObject("reuniao", reuniao);
        model.setViewName("/coordenador/criar-processo");
        return model;
    }

}
