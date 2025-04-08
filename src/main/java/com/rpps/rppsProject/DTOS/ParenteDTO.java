package com.rpps.rppsProject.DTOS;

import com.rpps.rppsProject.Model.Parente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ParenteDTO (Long idParente,
                         @NotBlank(message = "Nome parente não pode ser branco")
                         String nomeParente,
                          @NotBlank(message = "O CPF não pode estar vazio")
                          @Size(min = 11, max = 11, message = "O CPF deve ter exatamente 11 dígitos")
                          @Pattern(regexp = "\\d{11}", message = "O CPF deve conter apenas números")
                          String cpfParente) {
    public static Parente mapearParaParente(ParenteDTO parenteDTO) {
        Parente parente = new Parente();

        parente.setCpfParente(parenteDTO.cpfParente());
        parente.setIdParente(parenteDTO.idParente());
        parente.setNomeParente(parenteDTO.nomeParente());

        return parente;
    }
}
