package com.rpps.rppsProject.Controller;

import com.rpps.rppsProject.DTOS.ContribuicaoDTO;
import com.rpps.rppsProject.Service.ContribuicaoService;
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
    public ResponseEntity<ContribuicaoDTO> buscarPorId(@PathVariable Long id) {
        ContribuicaoDTO dto = contribuicaoService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<ContribuicaoDTO>> listarTodas() {
        List<ContribuicaoDTO> lista = contribuicaoService.listarContribuicoes();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/cpf/{cpfContribuinte}")
    public ResponseEntity<List<ContribuicaoDTO>> listarPeloCPF(@PathVariable String cpfContribuinte) {
        List<ContribuicaoDTO> listaContribuicoes = contribuicaoService.listarContribuicoesPorCPF(cpfContribuinte);
        return ResponseEntity.ok(listaContribuicoes);
    }
}
