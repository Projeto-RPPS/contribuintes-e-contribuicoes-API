package com.rpps.rppsProject.controller.contribuinte;

import com.rpps.rppsProject.dto.contribuinte.DadosGenealogicosDTO;
import com.rpps.rppsProject.dto.contribuinte.FiliacaoDTO;
import com.rpps.rppsProject.model.contribuinte.Filiacao;
import com.rpps.rppsProject.service.contribuinte.FiliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filiacoes")
public class FiliacaoController {

    @Autowired
    private FiliacaoService filiacaoService;

    @PostMapping
    public ResponseEntity<Long> cadastrarFiliacao(@RequestBody FiliacaoDTO dto) {
        Long id = filiacaoService.cadastrarFiliacao(dto);
        return ResponseEntity.ok(id);
    }

    @PutMapping
    public ResponseEntity<Long> atualizarFiliacao(@RequestBody Filiacao filiacao) {
        Long id = filiacaoService.atualizarFiliacao(filiacao);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFiliacao(@PathVariable Long id) {
        filiacaoService.deletarFiliacao(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filiacao> buscarPorId(@PathVariable Long id) {
        Filiacao filiacao = filiacaoService.buscarPorId(id);
        return ResponseEntity.ok(filiacao);
    }

    @GetMapping
    public ResponseEntity<List<Filiacao>> listarTodas() {
        List<Filiacao> filiacoes = filiacaoService.listarTodas();
        return ResponseEntity.ok(filiacoes);
    }

    @GetMapping("/arvore/{idContribuinte}")
    public ResponseEntity<List<DadosGenealogicosDTO>> obterArvoreGenealogica(@PathVariable Long idContribuinte) {
        List<DadosGenealogicosDTO> arvore = filiacaoService.obterArvoreGenealogica(idContribuinte);
        return ResponseEntity.ok(arvore);
    }

    @GetMapping("/arvore/porCpf/{cpf}")
    public ResponseEntity<List<DadosGenealogicosDTO>> obterArvoreGenealogicaPorCpf(@PathVariable String cpf) {
        List<DadosGenealogicosDTO> arvore = filiacaoService.obterArvoreGenealogicaPorCpf(cpf);
        return ResponseEntity.ok(arvore);
    }
}
