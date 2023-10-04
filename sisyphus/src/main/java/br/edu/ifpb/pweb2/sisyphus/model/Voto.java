package br.edu.ifpb.pweb2.sisyphus.model;

public class Voto {
    private int id;
    private boolean ausente;

    public Voto() {

    }

    public Voto(boolean ausente) {
        this.ausente = ausente;
    }

    public boolean isAusente() {
        return ausente;
    }
}
