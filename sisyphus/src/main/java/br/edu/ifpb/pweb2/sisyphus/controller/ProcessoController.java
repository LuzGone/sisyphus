package br.edu.ifpb.pweb2.sisyphus.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ifpb.pweb2.sisyphus.model.Processo;
import br.edu.ifpb.pweb2.sisyphus.service.ProcessoService;

@Controller
@RequestMapping("/processos")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    // ### UPLOAD ###
    @PostMapping("/{processoId}/upload")
    public ResponseEntity<String> uploadParecer(
            @PathVariable Long processoId,
            @RequestParam("parecer") MultipartFile parecerArquivo) {

        Processo processo = processoService.findById(processoId);

        try {
            processo.setParecer(parecerArquivo.getBytes());
            processoService.save(processo);
            return ResponseEntity.ok("Parecer enviado com sucesso!");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Erro ao processar o parecer.");
        }
    }
    // ### UPLOAD ###
}
