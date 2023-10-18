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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataReuniao() {
        return dataReuniao;
    }

    public void setDataReuniao(Date dataReuniao) {
        this.dataReuniao = dataReuniao;
    }

    public StatusReuniao getStatus() {
        return status;
    }

    public void setStatus(StatusReuniao status) {
        this.status = status;
    }

    public byte[] getAta() {
        return ata;
    }

    public void setAta(byte[] ata) {
        this.ata = ata;
    }
}
