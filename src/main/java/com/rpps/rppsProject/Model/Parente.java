package com.rpps.rppsProject.Model;

public class Parente {
    private Long idParente;
    private String nomeParente;
    private String cpfParente;

    public Parente() {
    }

    public Parente(Long idParente, String nomeParente, String cpfParente) {
        this.idParente = idParente;
        this.nomeParente = nomeParente;
        this.cpfParente = cpfParente;
    }

    public Long getIdParente() {
        return idParente;
    }

    public void setIdParente(Long idParente) {
        this.idParente = idParente;
    }

    public String getNomeParente() {
        return nomeParente;
    }

    public void setNomeParente(String nomeParente) {
        this.nomeParente = nomeParente;
    }

    public String getCpfParente() {
        return cpfParente;
    }

    public void setCpfParente(String cpfParente) {
        this.cpfParente = cpfParente;
    }
}
