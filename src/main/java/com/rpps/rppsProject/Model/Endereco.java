package com.rpps.rppsProject.Model;

public class Endereco {
    private Long idEndereco;
    private Long idContribuinte;
    private String cep;
    private String numero;
    private String estado;

    public Endereco() {
    }

    public Endereco(Long idEndereco, Long idContribuinte, String cep, String numero, String estado) {
        this.idEndereco = idEndereco;
        this.idContribuinte = idContribuinte;
        this.cep = cep;
        this.numero = numero;
        this.estado = estado;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Long getIdContribuinte() {
        return idContribuinte;
    }

    public void setIdContribuinte(Long idContribuinte) {
        this.idContribuinte = idContribuinte;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
