package com.rpps.rppsProject.DTOS;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record HistoricoSalarioMinimoDTO(Long idSalarioMinimo,
                                        @NotNull(message = "Valor do salário mínimo não pode ser nulo")
                                        BigDecimal valorSalarioMinimo,
                                        @NotNull(message = "Data de inicio de vigencia salario minimo não pode ser nula")
                                        LocalDate dataInicioVigencia,
                                        LocalDate dataFinalVigencia) {
}
