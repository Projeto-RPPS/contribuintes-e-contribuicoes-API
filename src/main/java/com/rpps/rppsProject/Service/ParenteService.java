package com.rpps.rppsProject.service;

import com.rpps.rppsProject.Model.Parente;
import com.rpps.rppsProject.Repository.ParenteRepository;
import com.rpps.rppsProject.DTOS.ParenteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParenteService {

    @Autowired
    private ParenteRepository repository;

    public Long cadastrarParente(ParenteDTO dto) {
        Parente parente = new Parente();
        parente.setNomeParente(dto.nomeParente());
        parente.setCpfParente(dto.cpfParente());
        return repository.insert(parente);
    }

    public Long atualizarParente(ParenteDTO dto) {
        Parente parente = new Parente();
        parente.setIdParente(dto.idParente());
        parente.setNomeParente(dto.nomeParente());
        parente.setCpfParente(dto.cpfParente());
        return repository.update(parente);
    }

    public void excluirParente(Long id) {
        repository.delete(id);
    }

    public ParenteDTO buscarPorId(Long id) {
        Optional<Parente> parente = repository.findById(id);
        return parente.map(p -> new ParenteDTO(p.getIdParente(), p.getNomeParente(), p.getCpfParente()))
                .orElseThrow(() -> new RuntimeException("Parente não encontrado"));
    }

    public List<ParenteDTO> listarTodos() {
        return repository.findAll().stream()
                .map(p -> new ParenteDTO(p.getIdParente(), p.getNomeParente(), p.getCpfParente()))
                .collect(Collectors.toList());
    }
}
