package com.rpps.rppsProject.DTOS;

import com.rpps.rppsProject.Model.Telefone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TelefoneDTO(Long idTelefone,
                          @NotNull(message = "Id contribuinte não pode ser nulo")
                          Long idContribuinte,
                          @NotBlank(message = "Numero de telefone não pode estar vazio")
                          String numeroTelefone,
                          @NotBlank(message = "Tipo de telefone não pode estar em branco")
                          String tipoTelefone) {
    public static Telefone mapearParaTelefone(TelefoneDTO telefoneDTO) {
        Telefone telefone = new Telefone();

        telefone.setTipoTelefone(telefoneDTO.tipoTelefone());
        telefone.setNumero(telefoneDTO.numeroTelefone());
        telefone.setIdContribuinte(telefoneDTO.idContribuinte());
        telefone.setIdTelefone(telefoneDTO.idTelefone());

        return telefone;
    }
}
