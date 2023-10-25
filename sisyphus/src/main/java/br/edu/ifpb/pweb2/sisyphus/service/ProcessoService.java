package br.edu.ifpb.pweb2.sisyphus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sisyphus.repository.ProcessoRepository;
import br.edu.ifpb.pweb2.sisyphus.model.Aluno;
import br.edu.ifpb.pweb2.sisyphus.model.EstadoProcesso;
import br.edu.ifpb.pweb2.sisyphus.model.Processo;

import java.util.Date;
import java.util.List;

@Service
public class ProcessoService {
    @Autowired
    private ProcessoRepository processoRepository;

    public List<Processo> getProcessos(){
        return this.processoRepository.findAll();
    }

    public List<Processo> getProcessosPorAluno(Aluno aluno){
        return this.processoRepository.findByAluno(aluno);
    }

    public Processo salvarProcesso(Processo processo){
        processo.getAluno().adicionarProcesso(processo);
        processo.setEstadoProcesso(EstadoProcesso.CRIADO);
        processo.setDataCriacao(new Date());
        processo.setNumero(""+new Date().getTime());
        return this.processoRepository.save(processo);
    }
}
