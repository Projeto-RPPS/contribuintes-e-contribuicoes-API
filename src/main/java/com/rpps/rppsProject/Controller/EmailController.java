package com.rpps.rppsProject.Controller;

import com.rpps.rppsProject.DTOS.EmailDTO;
import com.rpps.rppsProject.Service.EmailService;
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
    public ResponseEntity<Void> atualizar(@RequestBody @Valid EmailDTO dto) {
        emailService.atualizarEmail(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        emailService.deletarEmail(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmailDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(emailService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<EmailDTO>> listarTodos() {
        List<EmailDTO> emails = emailService.listarTodos();
        return ResponseEntity.ok(emails);
    }
}
