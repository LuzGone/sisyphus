package br.edu.ifpb.pweb2.sisyphus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sisyphus.repository.ColegiadoRepository;
import br.edu.ifpb.pweb2.sisyphus.model.Colegiado;
import br.edu.ifpb.pweb2.sisyphus.model.Coordenador;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;

import java.util.Date;
import java.util.List;

@Service
public class ColegiadoService {
    @Autowired
    private ColegiadoRepository colegiadoRepository;

    public List<Colegiado> getColegiados(){
        return this.colegiadoRepository.findAll();
    }

    public Page<Colegiado> getColegiados(Pageable pageable){
        return this.colegiadoRepository.findAll(pageable);
    }

    public Colegiado getColegiadoPorId(Long id){
        return this.colegiadoRepository.findById(id).orElse(null);
    }

    public Colegiado getColegiadoPorCoordenador(Coordenador coordenador){
        return this.colegiadoRepository.findByCoordenador(coordenador).get(0);
    }

    public Colegiado salvarColegiado(Colegiado colegiado){
        Coordenador coordenador = colegiado.getCoordenador();
        Professor professorCoordenador = coordenador.getProfessor();
        professorCoordenador.adicionarColegiado(colegiado);
        for(Professor professor : colegiado.getMembros() ){
            professor.adicionarColegiado(colegiado);
        }
        colegiado.setDataDoInicio(new Date());
        return this.colegiadoRepository.save(colegiado);
    }

    public void deletarColegiado(Long id){
        this.colegiadoRepository.deleteById(id);
    }
}
