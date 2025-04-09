package com.rpps.rppsProject.controller.contribuinte;

import com.rpps.rppsProject.dto.contribuinte.TelefoneDTO;
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
    public ResponseEntity<Void> atualizarTelefone(@RequestBody TelefoneDTO dto) {
        telefoneService.atualizarTelefone(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTelefone(@PathVariable Long id) {
        telefoneService.deletarTelefone(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelefoneDTO> buscarPorId(@PathVariable Long id) {
        TelefoneDTO dto = telefoneService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<TelefoneDTO>> listarTodos() {
        List<TelefoneDTO> telefones = telefoneService.listarTodos();
        return ResponseEntity.ok(telefones);
    }
}
