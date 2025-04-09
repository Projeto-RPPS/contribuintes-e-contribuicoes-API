package com.rpps.rppsProject.dto.contribuinte;

import com.rpps.rppsProject.model.contribuinte.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmailDTO(Long idEmail,
                       @NotNull(message = "Id contribuinte não pode ser nulo")
                       Long idContribuinte,
                       @NotBlank(message = "Email não pode ser branco")
                       String email) {
    public static Email mapearParaEmail(EmailDTO emailDTO) {
        Email email = new Email();

        email.setEmail(emailDTO.email());
        email.setIdEmail(emailDTO.idEmail());
        email.setIdContribuinte(emailDTO.idContribuinte());

        return email;
    }
}
