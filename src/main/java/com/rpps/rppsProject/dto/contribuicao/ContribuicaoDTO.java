package com.rpps.rppsProject.dto.contribuicao;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContribuicaoDTO(@NotNull(message = "Id contribuinte não pode ser null")
                              Long idContribuinte,
                              @NotNull(message = "A data referente a contribuição é obrigatória")
                              LocalDate dataReferencia) {
}
