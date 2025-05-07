package com.rpps.rppsProject.dto.contribuicao;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ContribuicaoCpfDTO(@NotEmpty(message = "Cpf não pode ser null")
                                 String cpf,
                                 @NotNull(message = "A data referente a contribuição é obrigatória")
                                 LocalDate dataReferencia) {
}

