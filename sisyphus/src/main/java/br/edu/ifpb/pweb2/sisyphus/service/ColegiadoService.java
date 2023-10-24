package br.edu.ifpb.pweb2.sisyphus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sisyphus.repository.ColegiadoRepository;
import br.edu.ifpb.pweb2.sisyphus.model.Colegiado;
import java.util.List;

@Service
public class ColegiadoService {
    @Autowired
    private ColegiadoRepository colegiadoRepository;

    public List<Colegiado> getColegiados(){
        return this.colegiadoRepository.findAll();
    }

    public Colegiado getColegiadoPorId(Long id){
        return this.colegiadoRepository.findById(id).orElse(null);
    }

    public Colegiado salvarColegiado(Colegiado colegiado){
        return this.colegiadoRepository.save(colegiado);
    }

    public void deletarColegiado(Long id){
        this.colegiadoRepository.deleteById(id);
    }
}
