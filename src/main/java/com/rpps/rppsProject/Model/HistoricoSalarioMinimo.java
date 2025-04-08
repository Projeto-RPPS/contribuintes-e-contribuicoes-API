package com.rpps.rppsProject.Model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class HistoricoSalarioMinimo {
    private Long idSalarioMinimo;
    private BigDecimal valor;
    private LocalDate dataInicioVigencia;
    private LocalDate dataFimVigencia;

    public HistoricoSalarioMinimo() {
    }

    public HistoricoSalarioMinimo(Long idSalarioMinimo, BigDecimal valor, LocalDate dataInicioVigencia, LocalDate dataFimVigencia) {
        this.idSalarioMinimo = idSalarioMinimo;
        this.valor = valor;
        this.dataInicioVigencia = dataInicioVigencia;
        this.dataFimVigencia = dataFimVigencia;
    }

    public Long getIdSalarioMinimo() {
        return idSalarioMinimo;
    }

    public void setIdSalarioMinimo(Long idSalarioMinimo) {
        this.idSalarioMinimo = idSalarioMinimo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor.setScale(2, RoundingMode.HALF_EVEN);
    }

    public LocalDate getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    public void setDataInicioVigencia(LocalDate dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }

    public LocalDate getDataFimVigencia() {
        return dataFimVigencia;
    }

    public void setDataFimVigencia(LocalDate dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia;
    }
}
