package com.rpps.rppsProject.Model;

public class Telefone {
    private Long idTelefone;
    private Long idContribuinte;
    private String numero;
    private String tipoTelefone;

    public Telefone() {
    }

    public Telefone(Long idTelefone, Long idContribuinte, String numero, String tipoTelefone) {
        this.idTelefone = idTelefone;
        this.idContribuinte = idContribuinte;
        this.numero = numero;
        this.tipoTelefone = tipoTelefone;
    }

    public Long getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(Long idTelefone) {
        this.idTelefone = idTelefone;
    }

    public Long getIdContribuinte() {
        return idContribuinte;
    }

    public void setIdContribuinte(Long idContribuinte) {
        this.idContribuinte = idContribuinte;
    }

    public String getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(String tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
