package com.rpps.rppsProject.Model;

public class Contribuinte {
    private Long idContribuinte;
    private String nomeSocial;
    private String nomeCivil;
    private String cpf;
    private Boolean ativo = true;
    private Long idCategoria;

    public Contribuinte() {
    }

    public Contribuinte(Long idContribuinte, String nomeSocial, String nomeCivil, String cpf, Boolean ativo, Long idCategoria) {
        this.idContribuinte = idContribuinte;
        this.nomeSocial = nomeSocial;
        this.nomeCivil = nomeCivil;
        this.cpf = cpf;
        this.ativo = ativo;
        this.idCategoria = idCategoria;
    }

    public Long getIdContribuinte() {
        return idContribuinte;
    }

    public void setIdContribuinte(Long idContribuinte) {
        this.idContribuinte = idContribuinte;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public String getNomeCivil() {
        return nomeCivil;
    }

    public void setNomeCivil(String nomeCivil) {
        this.nomeCivil = nomeCivil;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getIsAtivo() {
        return ativo;
    }

    public void setIsAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }
}