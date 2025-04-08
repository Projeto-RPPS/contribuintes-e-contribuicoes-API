package com.rpps.rppsProject.Controller;

import com.rpps.rppsProject.DTOS.DadosGenealogicosDTO;
import com.rpps.rppsProject.DTOS.FiliacaoDTO;
import com.rpps.rppsProject.Service.FiliacaoService;
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
    public ResponseEntity<Long> atualizarFiliacao(@RequestBody FiliacaoDTO dto) {
        Long id = filiacaoService.atualizarFiliacao(dto);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFiliacao(@PathVariable Long id) {
        filiacaoService.deletarFiliacao(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FiliacaoDTO> buscarPorId(@PathVariable Long id) {
        FiliacaoDTO dto = filiacaoService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<FiliacaoDTO>> listarTodas() {
        List<FiliacaoDTO> filiacoes = filiacaoService.listarTodas();
        return ResponseEntity.ok(filiacoes);
    }

    @GetMapping("/arvore/{idContribuinte}")
    public ResponseEntity<List<DadosGenealogicosDTO>> obterArvoreGenealogica(@PathVariable Long idContribuinte) {
        List<DadosGenealogicosDTO> arvore = filiacaoService.obterArvoreGenealogica(idContribuinte);
        return ResponseEntity.ok(arvore);
    }
}
