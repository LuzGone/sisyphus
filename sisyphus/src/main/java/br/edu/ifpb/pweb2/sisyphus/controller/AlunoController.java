package br.edu.ifpb.pweb2.sisyphus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.edu.ifpb.pweb2.sisyphus.model.Aluno;
import br.edu.ifpb.pweb2.sisyphus.service.AlunoService;
import java.util.List;

@Controller
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public List<Aluno> listarAlunos() {
        return alunoService.listarTodosAlunos();
    }

    @GetMapping("/{id}")
    public Aluno obterAluno(@PathVariable int id) {
        return alunoService.encontrarAlunoPorId(id);
    }

    @PostMapping
    public Aluno criarAluno(@RequestBody Aluno aluno) {
        return alunoService.criarAluno(aluno);
    }

    @DeleteMapping("/{id}")
    public void deletarAluno(@PathVariable int id) {
        alunoService.deletarAluno(id);
    }

}
