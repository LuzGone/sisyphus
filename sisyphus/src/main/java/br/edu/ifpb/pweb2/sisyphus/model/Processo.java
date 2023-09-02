package br.edu.ifpb.pweb2.sisyphus.model;

import java.util.Date;

public class Processo {
    private int id;
    private String numero;
    private Date dataRecepcao;
    private Date dataDistribuicao;
    private Date dataParecer;
    private byte[] parecer;

    public Processo() {

    }

    public Processo(String numero, Date dataRecepcao, Date dataDistribuicao, Date dataParecer, byte[] parecer) {
        this.numero = numero;
        this.dataRecepcao = dataRecepcao;
        this.dataDistribuicao = dataDistribuicao;
        this.dataParecer = dataParecer;
        this.parecer = parecer;
        
    }

}
