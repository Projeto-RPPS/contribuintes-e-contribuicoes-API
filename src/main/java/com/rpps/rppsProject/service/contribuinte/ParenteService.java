package com.rpps.rppsProject.service.contribuinte;

import com.rpps.rppsProject.model.contribuinte.Parente;
import com.rpps.rppsProject.repository.contribuinte.ParenteRepository;
import com.rpps.rppsProject.dto.contribuinte.ParenteDTO;
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

    public Long atualizarParente(Parente parente) {
        return repository.update(parente);
    }

    public void excluirParente(Long id) {
        repository.delete(id);
    }

    public Parente buscarPorId(Long id) {
        Optional<Parente> parente = repository.findById(id);
        return parente
                .orElseThrow(() -> new RuntimeException("Parente não encontrado"));
    }

    public List<Parente> listarTodos() {
        return repository.findAll();
    }
}
