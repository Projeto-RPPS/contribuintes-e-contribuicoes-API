package com.rpps.rppsProject.DTOS;

import java.util.List;

public record DadosCadastroDTO(ContribuinteDTO contribuinte,
                               List<EmailDTO> listaEmails,
                               List<EnderecoDTO> listaEnderecos,
                               List<TelefoneDTO> listaTelefones,
                               List<ParenteEParentescoDTO> listaParentes) {
}
