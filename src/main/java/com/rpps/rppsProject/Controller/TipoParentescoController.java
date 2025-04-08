package com.rpps.rppsProject.Controller;

import com.rpps.rppsProject.DTOS.TipoParentescoDTO;
import com.rpps.rppsProject.Service.TipoParentescoService;
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
    public ResponseEntity<Void> atualizar(@RequestBody @Valid TipoParentescoDTO tipoParentesco) {
        tipoParentescoService.atualizarTipoParentesco(tipoParentesco);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tipoParentescoService.deletarTipoParentesco(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoParentescoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tipoParentescoService.buscarPorId(id));

    }

    @GetMapping
    public ResponseEntity<List<TipoParentescoDTO>> listar () {
        return ResponseEntity.ok(tipoParentescoService.listarTodos());
    }
}
