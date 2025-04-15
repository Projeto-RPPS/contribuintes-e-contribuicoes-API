package com.rpps.rppsProject.controller.contribuinte;

import com.rpps.rppsProject.dto.contribuinte.TelefoneDTO;
import com.rpps.rppsProject.model.contribuinte.Telefone;
import com.rpps.rppsProject.service.contribuinte.TelefoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/telefones")
public class TelefoneController {

    @Autowired
    private TelefoneService telefoneService;

    @PostMapping
    public ResponseEntity<Void> cadastrarTelefone(@RequestBody TelefoneDTO dto) {
        telefoneService.cadastrarTelefone(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarTelefone(@RequestBody Telefone telefone) {
        telefoneService.atualizarTelefone(telefone);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTelefone(@PathVariable Long id) {
        telefoneService.deletarTelefone(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Telefone> buscarPorId(@PathVariable Long id) {
        Telefone telefone = telefoneService.buscarPorId(id);
        return ResponseEntity.ok(telefone);
    }

    @GetMapping
    public ResponseEntity<List<Telefone>> listarTodos() {
        List<Telefone> telefones = telefoneService.listarTodos();
        return ResponseEntity.ok(telefones);
    }
}
