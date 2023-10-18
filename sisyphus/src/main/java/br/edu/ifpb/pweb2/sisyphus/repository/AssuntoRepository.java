package br.edu.ifpb.pweb2.sisyphus.repository;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.pweb2.sisyphus.model.Assunto;

public class AssuntoRepository {
    private List<Assunto> assuntos = new ArrayList<>();
    private int nextId = 1;

    public Assunto criarAssunto(String nome) {
        Assunto novoAssunto = new Assunto(nextId, nome);
        nextId++;
        assuntos.add(novoAssunto);
        return novoAssunto;
    }

    public Assunto getAssuntoById(int id) {
        for (Assunto assunto : assuntos) {
            if (assunto.getId() == id) {
                return assunto;
            }
        }
        return null;
    }

    public List<Assunto> getAllAssuntos() {
        return assuntos;
    }

    public boolean atualizarAssunto(int id, String novoNome) {
        for (Assunto assunto : assuntos) {
            if (assunto.getId() == id) {
                assunto.setNome(novoNome);
                return true;
            }
        }
        return false;
    }

    public boolean excluirAssunto(int id) {
        Assunto assuntoParaRemover = null;
        for (Assunto assunto : assuntos) {
            if (assunto.getId() == id) {
                assuntoParaRemover = assunto;
                break;
            }
        }
        if (assuntoParaRemover != null) {
            assuntos.remove(assuntoParaRemover);
            return true;
        }
        return false;
    }
}
