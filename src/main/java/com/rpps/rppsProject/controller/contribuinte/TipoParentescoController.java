package com.rpps.rppsProject.controller.contribuinte;

import com.rpps.rppsProject.dto.contribuinte.TipoParentescoDTO;
import com.rpps.rppsProject.model.contribuinte.TipoParentesco;
import com.rpps.rppsProject.service.contribuinte.TipoParentescoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoDeParentesco")
public class TipoParentescoController {

    @Autowired
    TipoParentescoService tipoParentescoService;

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid TipoParentescoDTO tipoParentesco) {
        tipoParentescoService.cadastrarTipoParentesco(tipoParentesco);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizar(@RequestBody @Valid TipoParentesco tipoParentesco) {
        tipoParentescoService.atualizarTipoParentesco(tipoParentesco);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tipoParentescoService.deletarTipoParentesco(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoParentesco> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tipoParentescoService.buscarPorId(id));

    }

    @GetMapping
    public ResponseEntity<List<TipoParentesco>> listar () {
        return ResponseEntity.ok(tipoParentescoService.listarTodos());
    }
}
