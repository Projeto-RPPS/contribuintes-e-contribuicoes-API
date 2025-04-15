package com.rpps.rppsProject.controller.contribuinte;

import com.rpps.rppsProject.dto.contribuinte.EmailDTO;
import com.rpps.rppsProject.model.contribuinte.Email;
import com.rpps.rppsProject.service.contribuinte.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid EmailDTO dto) {
        emailService.cadastrarEmail(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizar(@RequestBody @Valid Email email) {
        emailService.atualizarEmail(email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        emailService.deletarEmail(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Email> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(emailService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Email>> listarTodos() {
        List<Email> emails = emailService.listarTodos();
        return ResponseEntity.ok(emails);
    }
}
