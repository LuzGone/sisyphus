package br.edu.ifpb.pweb2.sisyphus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sisyphus.model.Coordenador;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;
import br.edu.ifpb.pweb2.sisyphus.repository.CoordenadorRepository;

@Service
public class CoordenadorService {
    @Autowired
    private CoordenadorRepository coordenadorRepository;

    public List<Coordenador> getCoordenadores(){
        return this.coordenadorRepository.findAll();
    }

    public Coordenador getCoordenadorPorId(Long id){
        return this.coordenadorRepository.findById(id).orElse(null);
    }

    public Coordenador getCoordenadorPorProfessor(Professor professor){
        return this.coordenadorRepository.findByProfessor(professor);
    }

    public Coordenador salvarCoordenador(Coordenador coordenador){
        System.out.println(coordenador);
        return this.coordenadorRepository.save(coordenador);
    }

    public void deletarCoordenador(Long id){
        System.out.println(id);
        this.coordenadorRepository.deleteById(id);
    }

}
