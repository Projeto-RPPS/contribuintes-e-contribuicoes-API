package com.rpps.rppsProject.service.contribuinte;

import com.rpps.rppsProject.model.contribuinte.Email;
import com.rpps.rppsProject.repository.contribuinte.EmailRepository;
import com.rpps.rppsProject.dto.contribuinte.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    public void cadastrarEmail(EmailDTO dto) {
        Email email = new Email();
        email.setIdContribuinte(dto.idContribuinte());
        email.setEmail(dto.email());
        emailRepository.insert(email);
    }

    public void atualizarEmail(EmailDTO dto) {
        Email email = new Email();
        email.setIdEmail(dto.idEmail());
        email.setEmail(dto.email());
        emailRepository.update(email);
    }

    public void deletarEmail(Long idEmail) {
        emailRepository.delete(idEmail);
    }

    public EmailDTO buscarPorId(Long idEmail) {
        return emailRepository.findById(idEmail)
                .map(email -> new EmailDTO(
                        email.getIdEmail(),
                        email.getIdContribuinte(),
                        email.getEmail()
                )).orElseThrow(() -> new RuntimeException("Email não encontrado"));
    }

    public List<EmailDTO> listarTodos() {
        return emailRepository.findAll().stream()
                .map(email -> new EmailDTO(
                        email.getIdEmail(),
                        email.getIdContribuinte(),
                        email.getEmail()
                ))
                .toList();
    }
}
