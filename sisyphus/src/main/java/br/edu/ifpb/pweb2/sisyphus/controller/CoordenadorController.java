package br.edu.ifpb.pweb2.sisyphus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.sisyphus.service.AlunoService;
import br.edu.ifpb.pweb2.sisyphus.service.ColegiadoService;
import br.edu.ifpb.pweb2.sisyphus.service.CoordenadorService;
import br.edu.ifpb.pweb2.sisyphus.service.ProcessoService;
import br.edu.ifpb.pweb2.sisyphus.service.ProfessorService;
import br.edu.ifpb.pweb2.sisyphus.service.ReuniaoService;
import br.edu.ifpb.pweb2.sisyphus.ui.NavPage;
import br.edu.ifpb.pweb2.sisyphus.ui.NavePageBuilder;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.pweb2.sisyphus.model.Aluno;
import br.edu.ifpb.pweb2.sisyphus.model.Colegiado;
import br.edu.ifpb.pweb2.sisyphus.model.Coordenador;
import br.edu.ifpb.pweb2.sisyphus.model.EstadoProcesso;
import br.edu.ifpb.pweb2.sisyphus.model.Processo;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;
import br.edu.ifpb.pweb2.sisyphus.model.Reuniao;
import br.edu.ifpb.pweb2.sisyphus.model.StatusReuniao;
import br.edu.ifpb.pweb2.sisyphus.model.TipoVoto;
import br.edu.ifpb.pweb2.sisyphus.model.Voto;


@Controller
@RequestMapping("/coordenador/{id}")
@PreAuthorize("hasAnyRole('ADMIN', 'COORDENADOR')")
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

    @Autowired
    private ReuniaoService reuniaoService;

    @ModelAttribute("comRelator")
    public TipoVoto getComRelator(){
        return TipoVoto.COM_RELATOR;
    }

    @ModelAttribute("divergente")
    public TipoVoto getDivergente(){
        return TipoVoto.DIVERGENTE;
    }

    @ModelAttribute("ausente")
    public TipoVoto getAusente(){
        return TipoVoto.AUSENTE;
    }

    @ModelAttribute("emJulgamento")
    public EstadoProcesso getEmJulgamento(){
        return EstadoProcesso.EM_JULGAMENTO;
    }

    @ModelAttribute("programada")
    public StatusReuniao getProgramada(){
        return StatusReuniao.PROGRAMADA;
    }

    @ModelAttribute("emAndamento")
    public StatusReuniao getEmAndamento(){
        return StatusReuniao.EM_ANDAMENTO;
    }

    @ModelAttribute("encerrada")
    public StatusReuniao getEncerrada(){
        return StatusReuniao.ENCERRADA;
    }

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


    //------ HOME -------
    @GetMapping
    public ModelAndView paginaIncial(ModelAndView model){
        model.setViewName("/coordenador/home");
        return model;
    }


    //------ PROCESSOS ---------

    @GetMapping("processos")
    public ModelAndView mostrarPainelDeProcessos(ModelAndView model,
    @RequestParam(defaultValue = "1") int page,
    @RequestParam(defaultValue = "5") int size){
        Pageable paging = PageRequest.of(page - 1, size);
        Page<Processo> pageProcesso = processoService.getProcessos(paging);
        NavPage navPage = NavePageBuilder.newNavPage(pageProcesso.getNumber() + 1, pageProcesso.getTotalElements(), pageProcesso.getTotalPages(), size);
        model.addObject("navPage", navPage);
        model.addObject("processos", pageProcesso);
        model.setViewName("/coordenador/painel-processos");
        return model;
    }

    @GetMapping("processos/{idProcesso}")
    public ModelAndView vizualizarProcesso(ModelAndView model, @PathVariable("idProcesso") Long id){
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
    public ModelAndView mostrarPainelDeReuniaos(ModelAndView model, 
    @PathVariable("id") Long id,
    @RequestParam(defaultValue = "1") int page,
    @RequestParam(defaultValue = "5") int size){
        Coordenador coordenador = coordenadorService.getCoordenadorPorId(id);
        Colegiado colegiado = colegiadoService.getColegiadoPorCoordenador(coordenador);
        Pageable paging = PageRequest.of(page - 1, size);
        Page<Reuniao> pageReunioes = reuniaoService.getReunioesPorColegiado(colegiado,paging);
        NavPage navPage = NavePageBuilder.newNavPage(pageReunioes.getNumber() + 1, pageReunioes.getTotalElements(), pageReunioes.getTotalPages(), size);
        model.addObject("navPage", navPage);
        model.addObject("reunioes", pageReunioes);
        model.setViewName("/coordenador/painel-reunioes");
        return model;
    }

    @GetMapping("reunioes/criar")
    public ModelAndView criarReuniao(ModelAndView model,@PathVariable("id")Long id){
        List<Processo> processosDisponiveis = new ArrayList<Processo>();
        Coordenador coordenador = coordenadorService.getCoordenadorPorId(id);
        Colegiado colegiado = colegiadoService.getColegiadoPorCoordenador(coordenador);

        for(Processo processo : colegiado.getProcessos()){
            if(processo.getRelator()!= null){
                processosDisponiveis.add(processo);
            }
        }

        List<Processo> processosEscolhidos = new ArrayList<Processo>();
        for(int i=0;i<5;i++){
            processosEscolhidos.add(new Processo());
        }
        Reuniao reuniao = new Reuniao(colegiado,processosEscolhidos);
        System.out.println(reuniao.getColegiado());
        model.addObject("colegiado", colegiado);
        model.addObject("processosEscolhidos", processosEscolhidos);
        model.addObject("processosDisponiveis", processosDisponiveis);
        model.addObject("reuniao", reuniao);
        model.setViewName("/coordenador/criar-reuniao");
        return model;
    }

    @PostMapping("reunioes/criar")
    public ModelAndView salvarReuniao(
        @Valid Reuniao reuniao,
        BindingResult validation, 
        ModelAndView model,
        @PathVariable("id")Long id, 
        RedirectAttributes redirectAttributes
        ){
        if (validation.hasErrors()) {
            List<Processo> processosDisponiveis = new ArrayList<Processo>();
            Coordenador coordenador = coordenadorService.getCoordenadorPorId(id);
            Colegiado colegiado = colegiadoService.getColegiadoPorCoordenador(coordenador);

            for(Processo processo : colegiado.getProcessos()){
                if(processo.getRelator()!= null){
                    processosDisponiveis.add(processo);
                }
            }

            List<Processo> processosEscolhidos = new ArrayList<Processo>();
            for(int i=0;i<4;i++){
                processosEscolhidos.add(new Processo());
            }
            model.addObject("colegiado", colegiado);
            model.addObject("processosEscolhidos", processosEscolhidos);
            model.addObject("processosDisponiveis", processosDisponiveis);
            model.addObject("reuniao", reuniao);
            model.setViewName("/coordenador/criar-reuniao");
            return model;
        }
        Coordenador coordenador = coordenadorService.getCoordenadorPorId(id);
        Colegiado colegiado = colegiadoService.getColegiadoPorCoordenador(coordenador);
        reuniao.setColegiado(colegiado);
        reuniaoService.salvarReuniao(reuniao);
        System.out.println(reuniao.getColegiado()); 
        model.addObject("reunioes", colegiado.getReuniaos());
        model.setViewName("redirect:/coordenador/"+id+"/reunioes");
        redirectAttributes.addFlashAttribute("mensagem", "Reunião Criada com Sucesso");
        redirectAttributes.addFlashAttribute("reuniaoSalvos", true);
        return model;
    }

    //REUNIÃO
    @GetMapping("reunioes/{idReuniao}")
    public ModelAndView vizualizarReuniao(ModelAndView model, @PathVariable("id") Long id,@PathVariable("idReuniao") Long idReuniao){
        model.addObject("reuniao", this.reuniaoService.getReuniaoPorId(idReuniao));
        model.setViewName("/coordenador/reuniao");
        return model;
    }

    @PostMapping("reunioes/{idReuniao}/iniciar")
    public ModelAndView iniciarReuniao(Reuniao reuniao,ModelAndView model, @PathVariable("id") Long id,@PathVariable("idReuniao") Long idReuniao, RedirectAttributes redirectAttributes){
        try{
            this.reuniaoService.iniciarReuniao(idReuniao);
            Reuniao reuniao2 = this.reuniaoService.getReuniaoPorId(idReuniao);
            Processo processo = reuniao2.getProcessos().get(0);
            model.addObject("processo", processo);
            model.addObject("reuniao", this.reuniaoService.getReuniaoPorId(idReuniao));
            model.setViewName("redirect:/coordenador/"+id+"/reunioes/"+idReuniao+"/painel/"+processo.getId());
            return model;
        }catch(Exception e){
            Coordenador coordenador = coordenadorService.getCoordenadorPorId(id);
            Colegiado colegiado = colegiadoService.getColegiadoPorCoordenador(coordenador);
            model.addObject("reunioes", colegiado.getReuniaos());
            model.setViewName("redirect:/coordenador/"+id+"/reunioes");
            redirectAttributes.addFlashAttribute("mensagem", e.getMessage());
            //redirectAttributes.addFlashAttribute("reuniaoIniciada", false);
            return model;
        }
        
    }

    @GetMapping("reunioes/{idReuniao}/painel")
    public ModelAndView mostrarPainelDaReuniao(ModelAndView model, @PathVariable("id") Long id,@PathVariable("idReuniao") Long idReuniao){
        Reuniao reuniao = this.reuniaoService.getReuniaoPorId(idReuniao);
        model.addObject("processo", reuniao.getProcessos().get(0));
        model.addObject("reuniao", reuniao);
        model.setViewName("/coordenador/painel-reuniao");
        return model;
    }

    @GetMapping("reunioes/{idReuniao}/painel/{idProcesso}")
    public ModelAndView mostrarPainelDaReuniaoEmProcessoEspecifico(
        ModelAndView model, 
        @PathVariable("id") Long id,
        @PathVariable("idReuniao") Long idReuniao, 
        @PathVariable("idProcesso") Long idProcesso
        ){
        Reuniao reuniao = this.reuniaoService.getReuniaoPorId(idReuniao);
        Processo processo = this.processoService.getProcessoPorId(idProcesso);
        Colegiado colegiado = reuniao.getColegiado();
        List<Voto> listaVotos = new ArrayList<Voto>();
        for(Professor membro: colegiado.getMembros()){
            Voto voto = new Voto();
            if(membro == processo.getRelator()){
                Coordenador coordenador = colegiado.getCoordenador();
                Professor professor = coordenador.getProfessor();
                voto.setProfessor(professor);
            }else{
                Professor professor = membro;
                voto.setProfessor(professor);
            }
            voto.setProcesso(processo);
            listaVotos.add(voto);
        }
        processo.setListaDeVotos(listaVotos);
        System.out.println(processo.getListaDeVotos());
        model.addObject("processo", processo);
        model.addObject("listaVotos", listaVotos);
        model.addObject("reuniao", reuniao);
        model.setViewName("/coordenador/painel-reuniao");
        return model;
    }

    @PostMapping("reunioes/{idReuniao}/painel/{idProcesso}")
    public ModelAndView julgarProcesso(
        Processo processo,
        ModelAndView model, 
        @PathVariable("id") Long id,
        @PathVariable("idReuniao") Long idReuniao, 
        @PathVariable("idProcesso") Long idProcesso
        ){
        System.out.println(processo.getListaDeVotos());
        processoService.julgarProcesso(processo, idProcesso);
        Processo novoProcesso = processoService.getProcessoPorId(idProcesso);
        Reuniao reuniao = this.reuniaoService.getReuniaoPorId(idReuniao);
        List<Voto> listaVotos = new ArrayList<Voto>();
        for(Professor membro: reuniao.getColegiado().getMembros()){
            Voto voto = new Voto();
            voto.setProcesso(novoProcesso);
            voto.setProfessor(membro);
            listaVotos.add(voto);
        }
        novoProcesso.setListaDeVotos(listaVotos);
        model.addObject("processo", novoProcesso);
        model.addObject("listaVotos", listaVotos);
        model.addObject("reuniao", reuniao);
        model.setViewName("redirect:/coordenador/"+id+"/reunioes/"+idReuniao+"/painel/"+idProcesso);
        return model;
    }

    @PostMapping("reunioes/{idReuniao}/painel/encerrar")
    public ModelAndView finalizarReuniao(Reuniao reuniao,ModelAndView model, @PathVariable("id") Long id, @PathVariable("idReuniao") Long idReuniao){
        reuniaoService.encerrarReuniao(idReuniao);
        Coordenador coordenador = coordenadorService.getCoordenadorPorId(id);
        Colegiado colegiado = colegiadoService.getColegiadoPorCoordenador(coordenador);
        model.addObject("reunioes", colegiado.getReuniaos());
        model.setViewName("redirect:/coordenador/"+id+"/reunioes");
        return model;
    }
    
}
