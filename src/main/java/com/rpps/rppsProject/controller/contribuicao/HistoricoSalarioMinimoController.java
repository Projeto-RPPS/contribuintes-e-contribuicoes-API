package com.rpps.rppsProject.controller.contribuicao;

import com.rpps.rppsProject.dto.contribuicao.HistoricoSalarioMinimoDTO;
import com.rpps.rppsProject.model.contribuicao.HistoricoSalarioMinimo;
import com.rpps.rppsProject.service.contribuicao.HistoricoSalarioMinimoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historicoSalarioMinimo")
public class HistoricoSalarioMinimoController {

    @Autowired
    private HistoricoSalarioMinimoService service;

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid HistoricoSalarioMinimoDTO dto) {
        service.cadastrarHistorico(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizar(@RequestBody @Valid HistoricoSalarioMinimo historicoSalarioMinimo) {
        service.atualizarHistorico(historicoSalarioMinimo);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluirHistorico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricoSalarioMinimo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<HistoricoSalarioMinimo>> listarTodos() {
        List<HistoricoSalarioMinimo> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }
}
