package com.rpps.rppsProject.Controller;

import com.rpps.rppsProject.DTOS.ContribuinteDTO;
import com.rpps.rppsProject.DTOS.DadosCadastroDTO;
import com.rpps.rppsProject.Service.ContribuinteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contribuintes")
public class ContribuinteController {

    @Autowired
    private ContribuinteService contribuinteService;

    @PostMapping
    public ResponseEntity<String> criarContribuinte(@RequestBody DadosCadastroDTO dadosCadastroDTO) {
        contribuinteService.cadastrarContribuinte(dadosCadastroDTO);
        return ResponseEntity.ok("Contribuinte cadastrado com sucesso.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContribuinteDTO> buscarPorId(@PathVariable Long id) {
        ContribuinteDTO dto = contribuinteService.buscarContribuintePorId(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<ContribuinteDTO>> listarTodos() {
        List<ContribuinteDTO> lista = contribuinteService.listarContribuintes();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> inativar(@PathVariable Long id) {
        contribuinteService.inativarContribuinte(id);
        return ResponseEntity.ok("Contribuinte inativado com sucesso.");
    }
}
