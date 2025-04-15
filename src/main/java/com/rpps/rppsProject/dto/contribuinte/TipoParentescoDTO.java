package com.rpps.rppsProject.dto.contribuinte;

import jakarta.validation.constraints.NotBlank;

public record TipoParentescoDTO(@NotBlank(message = "Descricao de tipo de parentesco é obrigatória")
                                String descricaoTipoParentesco) {
}
