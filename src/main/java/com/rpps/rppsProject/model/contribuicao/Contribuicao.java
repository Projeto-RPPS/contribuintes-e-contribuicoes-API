package com.rpps.rppsProject.model.contribuicao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Contribuicao {
    private Long idContribuicao;
    private Long idContribuinte;
    private LocalDate dataContribuicao;
    private BigDecimal valorContribuicao;
    private java.time.LocalDate dataReferencia;
    private Long idSalarioMinimo;

    public Contribuicao() {
    }

    public Contribuicao(Long idContribuicao, Long idContribuinte, LocalDate dataContribuicao, BigDecimal valorContribuicao, java.time.LocalDate dataReferencia, Long idSalarioMinimo) {
        this.idContribuicao = idContribuicao;
        this.idContribuinte = idContribuinte;
        this.dataContribuicao = dataContribuicao;
        this.valorContribuicao = valorContribuicao;
        this.dataReferencia = dataReferencia;
        this.idSalarioMinimo = idSalarioMinimo;
    }

    public Long getIdContribuicao() {
        return idContribuicao;
    }

    public void setIdContribuicao(Long idContribuicao) {
        this.idContribuicao = idContribuicao;
    }

    public Long getIdContribuinte() {
        return idContribuinte;
    }

    public void setIdContribuinte(Long idContribuinte) {
        this.idContribuinte = idContribuinte;
    }

    public LocalDate getDataContribuicao() {
        return dataContribuicao;
    }

    public void setDataContribuicao(LocalDate dataContribuicao) {
        this.dataContribuicao = dataContribuicao;
    }

    public BigDecimal getValorContribuicao() {
        return valorContribuicao;
    }

    public void setValorContribuicao(BigDecimal valorContribuicao) {
        this.valorContribuicao = valorContribuicao;
    }

    public java.time.LocalDate getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(java.time.LocalDate dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public Long getIdSalarioMinimo() {
        return idSalarioMinimo;
    }

    public void setIdSalarioMinimo(Long idSalarioMinimo) {
        this.idSalarioMinimo = idSalarioMinimo;
    }
}
