package br.edu.ifpb.pweb2.sisyphus.model;

import java.util.Date;

public class Reuniao {
    private int id;
    private Date dataReuniao;
    private StatusReuniao status;
    private byte[] ata;

    public Reuniao(int id, Date dataReuniao, StatusReuniao status, byte[] ata){
        this.id = id;
        this.dataReuniao = dataReuniao;
        this.status = status;
        this.ata= ata;
    }

}
