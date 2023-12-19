package br.edu.ifpb.pweb2.sisyphus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sisyphus.model.EstadoProcesso;
import br.edu.ifpb.pweb2.sisyphus.model.Processo;
import br.edu.ifpb.pweb2.sisyphus.model.Reuniao;
import br.edu.ifpb.pweb2.sisyphus.model.StatusReuniao;
import br.edu.ifpb.pweb2.sisyphus.repository.ReuniaoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReuniaoService {
    @Autowired
    private ReuniaoRepository reuniaoRepository;
    
    public List<Reuniao> getReunioes(){
        return this.reuniaoRepository.findAll();
    }

    public Reuniao getReuniaoPorId(Long id){
        return this.reuniaoRepository.findById(id).orElse(null);
    }

    public Reuniao iniciarReuniao(Long id) throws Exception{
        for(Reuniao reuniao2 : this.reuniaoRepository.findAll()){
            if(reuniao2.getStatus().equals(StatusReuniao.EM_ANDAMENTO)){
                throw new Exception("Já existe uma reunião em andamento!");
            }
        }
        Reuniao reuniaoAtualizada = this.reuniaoRepository.findById(id).orElse(null);
        reuniaoAtualizada.setStatus(StatusReuniao.EM_ANDAMENTO);
        for(Processo processo : reuniaoAtualizada.getProcessos()){
            processo.setEstadoProcesso(EstadoProcesso.EM_JULGAMENTO);
        }
        return this.reuniaoRepository.save(reuniaoAtualizada);
    }

    public Reuniao salvarReuniao(Reuniao reuniao){
        List<Processo> processosSelecionados = new ArrayList<Processo>();
        reuniao.setStatus(StatusReuniao.PROGRAMADA);
        reuniao.setCodigo(""+reuniao.getDataReuniao().getTime()+"/"+reuniao.getId());
        for (Processo processo : reuniao.getProcessos()){
            if (processo != null) {
                processo.setEstadoProcesso(EstadoProcesso.EM_PAUTA);
                processo.setReuniao(reuniao);
                processosSelecionados.add(processo);
            }
        }
        reuniao.setProcessos(processosSelecionados);
        reuniao.getColegiado().adicionarReuniao(reuniao);
        return this.reuniaoRepository.save(reuniao);
    }

    public Reuniao encerrarReuniao(Long id){
        Reuniao reuniaoAtualizada = this.reuniaoRepository.findById(id).orElse(null);
        reuniaoAtualizada.setStatus(StatusReuniao.ENCERRADA);
        return this.reuniaoRepository.save(reuniaoAtualizada);
    }

    public void apagarReuniao(Long id){
        this.reuniaoRepository.deleteById(id);
    }

    
}
