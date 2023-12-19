package br.edu.ifpb.pweb2.sisyphus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sisyphus.repository.ProcessoRepository;
import br.edu.ifpb.pweb2.sisyphus.model.Aluno;
import br.edu.ifpb.pweb2.sisyphus.model.Colegiado;
import br.edu.ifpb.pweb2.sisyphus.model.EstadoProcesso;
import br.edu.ifpb.pweb2.sisyphus.model.Processo;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;
import br.edu.ifpb.pweb2.sisyphus.model.TipoDecisao;
import br.edu.ifpb.pweb2.sisyphus.model.TipoVoto;
import br.edu.ifpb.pweb2.sisyphus.model.Voto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProcessoService {
    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private VotoService votoService;

    public List<Processo> getProcessos(){
        return this.processoRepository.findAll();
    }

    public List<Processo> getProcessosPorAluno(Aluno aluno){
        return this.processoRepository.findByAluno(aluno);
    }

    public List<Processo> getProcessosPorProfessor(Professor professor){
        return this.processoRepository.findByRelator(professor);
    }

    public Processo getProcessoPorId(Long id){
        return this.processoRepository.findById(id).orElse(null);
    }

    public Processo salvarProcesso(Processo processo){
        processo.getAluno().adicionarProcesso(processo);
        processo.setEstadoProcesso(EstadoProcesso.CRIADO);
        processo.setDataCriacao(new Date());
        processo.setNumero(""+new Date().getTime());
        return this.processoRepository.save(processo);
    }

    public Processo atualizarProcesso(Processo processo, Long id){
        Processo processoAtualizado = this.processoRepository.findById(id).orElse(new Processo());
        processoAtualizado.setJustificativaRelator(processo.getJustificativaRelator());
        processoAtualizado.setTipoDecisao(processo.getTipoDecisao());
        return this.processoRepository.save(processoAtualizado);
    }

    public void testandoProcesso(Processo processo){
        System.out.println(processo.getListaDeVotos());
    }

    public Processo atribuirProcesso(Processo processo,Long id){
        Processo processoAtualizado = this.processoRepository.findById(id).orElse(new Processo());
        processoAtualizado.setRelator(processo.getRelator());
        for (Colegiado colegiado : processo.getRelator().getListaColegiados()){
            if(colegiado.getCurso() == processo.getRelator().getCurso()){
                processoAtualizado.setColegiado(colegiado);
                break;
            }
        }
        processoAtualizado.setEstadoProcesso(EstadoProcesso.DISTRIBUIDO);
        processoAtualizado.setDataDistribuicao(new Date());
        return this.processoRepository.save(processoAtualizado);
    }

    public Processo julgarProcesso(Processo processo, Long id){
        Processo processoAtualizado = this.processoRepository.findById(id).orElse(new Processo());
        List<Voto> novaListaVotos = new ArrayList<Voto>();
        int comRelator = 1;
        int divergente = 0;
        for(Voto voto: processo.getListaDeVotos()){
            novaListaVotos.add(voto);
            if (voto.getTipoVoto() == TipoVoto.DIVERGENTE) {
                //System.out.println("O VOTO FOI DIVERGENTE");
                divergente+=1;
                //System.out.println(divergente);
            }
            if(voto.getTipoVoto() == TipoVoto.COM_RELATOR){
                //System.out.println("O VOTO FOI COM RELATOR");
                comRelator+=1;
                //System.out.println(comRelator);
            }
            votoService.salvarVoto(voto);
        }
        processoAtualizado.setListaDeVotos(novaListaVotos);
        if (divergente>comRelator) {
            //System.out.println("DIVERGENTE FOI A MAIORIA");
            if (processoAtualizado.getTipoDecisao() == TipoDecisao.DEFERIDO) {
                processoAtualizado.setTipoDecisao(TipoDecisao.INDEFERIDO);
            }
            if (processoAtualizado.getTipoDecisao() == TipoDecisao.INDEFERIDO) {
                processoAtualizado.setTipoDecisao(TipoDecisao.DEFERIDO);
            }
        }
        processoAtualizado.setEstadoProcesso(EstadoProcesso.JULGADO);
        return this.processoRepository.save(processoAtualizado);
    }

}
