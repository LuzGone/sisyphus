package br.edu.ifpb.pweb2.sisyphus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sisyphus.model.Administrador;
import br.edu.ifpb.pweb2.sisyphus.repository.AdministradorRepository;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository administradorRepository;

    public List<Administrador> getAssuntos(){
        return this.administradorRepository.findAll();
    }

    public Administrador getAssuntoPorId(Long id){
        return this.administradorRepository.findById(id).orElse(null);
    }

    public Administrador salvarAssunto(Administrador administrador){
        return this.administradorRepository.save(administrador);
    }

    public void deletarAssunto(Long id){
        this.administradorRepository.deleteById(id);
    }
}
