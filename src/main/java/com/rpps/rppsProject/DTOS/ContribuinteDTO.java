package com.rpps.rppsProject.DTOS;

import com.rpps.rppsProject.Model.Contribuinte;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ContribuinteDTO(
        Long idContribuinte,
        @NotBlank(message = "O nome civil não pode estar vazio")
        String nomeCivil,
        String nomeSocial,
        @NotBlank(message = "O CPF não pode estar vazio")
        @Size(min = 11, max = 11, message = "O CPF deve ter exatamente 11 dígitos")
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter apenas números")
        String cpf,
        Boolean ativo,
        @NotNull(message = "O valor da categoria não pode ser nulo")
        Long idCategoria
) {
        public static Contribuinte mapearParaContribuinte(ContribuinteDTO dto) {
                Contribuinte contribuinte = new Contribuinte();

                contribuinte.setIdContribuinte(dto.idContribuinte());
                contribuinte.setNomeCivil(dto.nomeCivil());
                contribuinte.setNomeSocial(dto.nomeSocial());
                contribuinte.setCpf(dto.cpf());
                contribuinte.setIsAtivo(dto.ativo());
                contribuinte.setIdCategoria(dto.idCategoria());

                return contribuinte;
        }

}
