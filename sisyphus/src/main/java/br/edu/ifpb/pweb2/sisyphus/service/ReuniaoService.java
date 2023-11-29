package br.edu.ifpb.pweb2.sisyphus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sisyphus.model.Reuniao;
import br.edu.ifpb.pweb2.sisyphus.repository.ReuniaoRepository;
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

    public Reuniao salvarReuniao(Reuniao reuniao){
        return this.reuniaoRepository.save(reuniao);
    }

    public void apagarReuniao(Long id){
        this.reuniaoRepository.deleteById(id);
    }

    
}