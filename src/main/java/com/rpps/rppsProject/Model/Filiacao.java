package com.rpps.rppsProject.Model;

public class Filiacao {
    private Long idFiliacao;
    private Long idContribuinte;
    private Long idTipoParentesco;
    private Long idParente;

    public Filiacao() {
    }

    public Filiacao(Long idFiliacao, Long idContribuinte, Long idTipoParentesco, Long idParente) {
        this.idFiliacao = idFiliacao;
        this.idContribuinte = idContribuinte;
        this.idTipoParentesco = idTipoParentesco;
        this.idParente = idParente;
    }

    public Long getIdFiliacao() {
        return idFiliacao;
    }

    public void setIdFiliacao(Long idFiliacao) {
        this.idFiliacao = idFiliacao;
    }

    public Long getIdContribuinte() {
        return idContribuinte;
    }

    public void setIdContribuinte(Long idContribuinte) {
        this.idContribuinte = idContribuinte;
    }

    public Long getIdTipoParentesco() {
        return idTipoParentesco;
    }

    public void setIdTipoParentesco(Long idTipoParentesco) {
        this.idTipoParentesco = idTipoParentesco;
    }

    public Long getIdParente() {
        return idParente;
    }

    public void setIdParente(Long idParente) {
        this.idParente = idParente;
    }
}
