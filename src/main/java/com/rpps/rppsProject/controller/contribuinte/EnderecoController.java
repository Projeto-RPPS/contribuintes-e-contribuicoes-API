package com.rpps.rppsProject.controller.contribuinte;

import com.rpps.rppsProject.dto.contribuinte.EnderecoDTO;
import com.rpps.rppsProject.model.contribuinte.Endereco;
import com.rpps.rppsProject.service.contribuinte.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid EnderecoDTO dto) {
        enderecoService.cadastrarEndereco(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizar(@RequestBody @Valid Endereco endereco) {
        enderecoService.atualizarEndereco(endereco);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        enderecoService.deletarEndereco(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(enderecoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> listarTodos() {
        List<Endereco> enderecos = enderecoService.listarTodos();
        return ResponseEntity.ok(enderecos);
    }
}
