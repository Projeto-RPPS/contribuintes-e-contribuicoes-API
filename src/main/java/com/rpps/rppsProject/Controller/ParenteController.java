package com.rpps.rppsProject.Controller;

import com.rpps.rppsProject.DTOS.ParenteDTO;
import com.rpps.rppsProject.service.ParenteService;
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
    public ResponseEntity<Void> atualizar(@RequestBody @Valid ParenteDTO parenteDTO) {
        parenteService.atualizarParente(parenteDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        parenteService.excluirParente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParenteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(parenteService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ParenteDTO>> listar() {
        return ResponseEntity.ok(parenteService.listarTodos());
    }
}
