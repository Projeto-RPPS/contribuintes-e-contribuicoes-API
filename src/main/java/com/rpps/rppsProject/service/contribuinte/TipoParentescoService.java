package com.rpps.rppsProject.service.contribuinte;

import com.rpps.rppsProject.model.contribuinte.TipoParentesco;
import com.rpps.rppsProject.repository.contribuinte.TipoParentescoRepository;
import com.rpps.rppsProject.dto.contribuinte.TipoParentescoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoParentescoService {

    @Autowired
    private TipoParentescoRepository repository;

    public void cadastrarTipoParentesco(TipoParentescoDTO dto) {
        TipoParentesco tipo = new TipoParentesco();
        tipo.setDescricaoParentesco(dto.descricaoTipoParentesco());
        repository.insert(tipo);
    }

    public void atualizarTipoParentesco(TipoParentesco tipoParentesco) {
        repository.update(tipoParentesco);
    }

    public void deletarTipoParentesco(Long id) {
        repository.delete(id);
    }

    public TipoParentesco buscarPorId(Long id) {
        Optional<TipoParentesco> tipoOpt = repository.findById(id);

        return tipoOpt
                .orElseThrow(() -> new RuntimeException("Tipo de Parentesco não encontrado para o ID " + id));
    }

    public List<TipoParentesco> listarTodos() {
        List<TipoParentesco> tipos = repository.findAll();

        return tipos;
    }
}
