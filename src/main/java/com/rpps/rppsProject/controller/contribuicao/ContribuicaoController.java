package com.rpps.rppsProject.controller.contribuicao;

import com.rpps.rppsProject.dto.contribuicao.ContribuicaoDTO;
import com.rpps.rppsProject.model.contribuicao.Contribuicao;
import com.rpps.rppsProject.service.contribuicao.ContribuicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contribuicoes")
public class ContribuicaoController {

    @Autowired
    private ContribuicaoService contribuicaoService;

    @PostMapping
    public ResponseEntity<String> realizarContribuicao(@RequestBody ContribuicaoDTO dto) {
        contribuicaoService.fazerContribuicao(dto);
        return ResponseEntity.ok("Contribuição realizada com sucesso.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        contribuicaoService.deletarContribuicao(id);
        return ResponseEntity.ok("Contribuição deletada com sucesso.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contribuicao> buscarPorId(@PathVariable Long id) {
        Contribuicao contribuicao = contribuicaoService.buscarPorId(id);
        return ResponseEntity.ok(contribuicao);
    }

    @GetMapping
    public ResponseEntity<List<Contribuicao>> listarTodas() {
        List<Contribuicao> lista = contribuicaoService.listarContribuicoes();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/cpf/{cpfContribuinte}")
    public ResponseEntity<List<Contribuicao>> listarPeloCPF(@PathVariable String cpfContribuinte) {
        List<Contribuicao> listaContribuicoes = contribuicaoService.listarContribuicoesPorCPF(cpfContribuinte);
        return ResponseEntity.ok(listaContribuicoes);
    }
}
