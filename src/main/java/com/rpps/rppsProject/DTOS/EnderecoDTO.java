package com.rpps.rppsProject.DTOS;

import com.rpps.rppsProject.Model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(Long idEndereco,
                          @NotNull(message = "Id contribuinte não pode ser nulo")
                          Long idContribuinte,
                          @NotBlank(message = "Cep não pode ser branco")
                          String cep,
                          @NotBlank(message = "Numero não pode ser branco")
                          String numeroMoradia,
                          @NotBlank(message = "Estado não pode ser branco")
                          String estado) {
    public static Endereco mapearParaEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();

        endereco.setEstado(enderecoDTO.estado());
        endereco.setIdEndereco(enderecoDTO.idEndereco());
        endereco.setCep(enderecoDTO.cep());
        endereco.setNumero(enderecoDTO.numeroMoradia());
        endereco.setIdContribuinte(enderecoDTO.idContribuinte());

        return endereco;
    }
}
