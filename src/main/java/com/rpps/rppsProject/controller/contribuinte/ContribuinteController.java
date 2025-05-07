package com.rpps.rppsProject.controller.contribuinte;

import com.rpps.rppsProject.dto.contribuinte.ContribuinteDTO;
import com.rpps.rppsProject.dto.contribuinte.DadosCadastroDTO;
import com.rpps.rppsProject.model.contribuinte.Contribuinte;
import com.rpps.rppsProject.service.contribuinte.ContribuinteService;
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
    public ResponseEntity<Contribuinte> buscarPorId(@PathVariable Long id) {
        Contribuinte contribuinte = contribuinteService.buscarContribuintePorId(id);
        return ResponseEntity.ok(contribuinte);
    }

    @GetMapping("/porCpf/{cpf}")
    public ResponseEntity<Contribuinte> buscarPorCpf(@PathVariable String cpf) {
        Contribuinte contribuinte = contribuinteService.buscarContribuintePorCpf(cpf);
        return ResponseEntity.ok(contribuinte);
    }

    @GetMapping
    public ResponseEntity<List<Contribuinte>> listarTodos() {
        List<Contribuinte> lista = contribuinteService.listarContribuintes();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> inativar(@PathVariable Long id) {
        contribuinteService.inativarContribuinte(id);
        return ResponseEntity.ok("Contribuinte inativado com sucesso.");
    }

    @DeleteMapping("/porCpf/{cpf}")
    public ResponseEntity<String> inativarPorCpf(@PathVariable String cpf) {
        contribuinteService.inativarContribuintePorCpf(cpf);
        return ResponseEntity.ok("Contribuinte inativado com sucesso.");
    }
}
