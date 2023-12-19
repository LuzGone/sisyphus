package br.edu.ifpb.pweb2.sisyphus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sisyphus.model.Voto;
import br.edu.ifpb.pweb2.sisyphus.repository.VotoRepository;

@Service
public class VotoService {
    @Autowired
    private VotoRepository votoRepository;

    public List<Voto> getVotos(){
        return this.votoRepository.findAll();
    }

    public Voto getVotoPorId(Long id){
        return this.votoRepository.findById(id).orElse(null);
    }

    public Voto salvarVoto(Voto voto){
        return this.votoRepository.save(voto);
    }

    public void deletarVoto(Long id){
        this.votoRepository.deleteById(id);
    }
}
