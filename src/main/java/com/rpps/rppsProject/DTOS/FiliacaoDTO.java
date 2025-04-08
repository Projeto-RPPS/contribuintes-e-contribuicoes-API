package com.rpps.rppsProject.DTOS;

import jakarta.validation.constraints.NotNull;

public record FiliacaoDTO(Long idFiliacao,
                          @NotNull(message = "Id do contribuinte não pode ser nulo")
                          Long idContribuinte,
                          @NotNull(message = "Id Tipo de Parentesco não pode ser null")
                          Long idTipoParentesco,
                          @NotNull(message = "Id parente não pode ser nulo")
                          Long idParente
) {
}
