package com.rpps.rppsProject.DTOS;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContribuicaoDTO(Long idContribuicao,
                              @NotNull(message = "Id contribuinte não pode ser null")
                              Long idContribuinte,
                              LocalDate dataContribuicao,
                              BigDecimal valorContribuicao,
                              @NotNull(message = "A data referente a contribuição é obrigatória")
                              LocalDate dataReferencia,
                              Long idSalarioMinimo
                              ) {
}
