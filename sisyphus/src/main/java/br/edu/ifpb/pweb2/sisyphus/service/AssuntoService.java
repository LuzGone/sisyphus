package br.edu.ifpb.pweb2.sisyphus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sisyphus.repository.AssuntoRepository;
import br.edu.ifpb.pweb2.sisyphus.model.Assunto;
import java.util.List;

@Service
public class AssuntoService {
    @Autowired
    private AssuntoRepository assuntoRepository;

    public List<Assunto> getAssuntos(){
        return this.assuntoRepository.findAll();
    }

    public Page<Assunto> getAssuntos(Pageable pageable){
        return this.assuntoRepository.findAll(pageable);
    }

    public Assunto getAssuntoPorId(Long id){
        return this.assuntoRepository.findById(id).orElse(null);
    }

    public Assunto salvarAssunto(Assunto assunto){
        return this.assuntoRepository.save(assunto);
    }

    public void deletarAssunto(Long id){
        this.assuntoRepository.deleteById(id);
    }
}
