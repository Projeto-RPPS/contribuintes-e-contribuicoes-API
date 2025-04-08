package com.rpps.rppsProject.DTOS;

import jakarta.validation.constraints.NotBlank;

public record TipoParentescoDTO(Long idTipoParentesco,
                                @NotBlank(message = "Descricao de tipo de parentesco é obrigatória")
                                String descricaoTipoParentesco) {
}
