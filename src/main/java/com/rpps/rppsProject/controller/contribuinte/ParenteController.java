package com.rpps.rppsProject.controller.contribuinte;

import com.rpps.rppsProject.dto.contribuinte.ParenteDTO;
import com.rpps.rppsProject.model.contribuinte.Parente;
import com.rpps.rppsProject.service.contribuinte.ParenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parentes")
public class ParenteController {

    @Autowired
    private ParenteService parenteService;

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid ParenteDTO parenteDTO) {
        parenteService.cadastrarParente(parenteDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizar(@RequestBody @Valid Parente parente) {
        parenteService.atualizarParente(parente);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        parenteService.excluirParente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parente> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(parenteService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Parente>> listar() {
        return ResponseEntity.ok(parenteService.listarTodos());
    }
}
