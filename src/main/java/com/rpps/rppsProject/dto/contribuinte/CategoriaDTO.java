package com.rpps.rppsProject.dto.contribuinte;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CategoriaDTO(@NotBlank(message = "A categoria precisa ter um nome")
                           String nomeCategoria,
                           @NotNull(message = "A categoria precisa ter um percentual de contribuição")
                           BigDecimal percentualContribuicao) {
}
