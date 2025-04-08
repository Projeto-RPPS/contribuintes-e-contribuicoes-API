package com.rpps.rppsProject.Model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Categoria {
    private Long idCategoria;
    private String nomeCategoria;
    private BigDecimal percentualContribuicao;

    public Categoria() {
    }

    public Categoria(Long idCategoria, String nomeCategoria, BigDecimal percentualContribuicao) {
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
        this.percentualContribuicao = percentualContribuicao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public BigDecimal getPercentualContribuicao() {
        return percentualContribuicao;
    }

    public void setPercentualContribuicaoAntesDeSalvar(BigDecimal percentualContribuicao) {
        this.percentualContribuicao = percentualContribuicao.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }

    public void setPercentualContribuicao(BigDecimal percentualContribuicao) {
        this.percentualContribuicao = percentualContribuicao.setScale(2, RoundingMode.HALF_UP);
    }
}