package com.rpps.rppsProject.Service;

import com.rpps.rppsProject.Model.TipoParentesco;
import com.rpps.rppsProject.Repository.TipoParentescoRepository;
import com.rpps.rppsProject.DTOS.TipoParentescoDTO;
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

    public void atualizarTipoParentesco(TipoParentescoDTO dto) {
        TipoParentesco tipo = new TipoParentesco();
        tipo.setIdTipoParentesco(dto.idTipoParentesco());
        tipo.setDescricaoParentesco(dto.descricaoTipoParentesco());
        repository.update(tipo);
    }

    public void deletarTipoParentesco(Long id) {
        repository.delete(id);
    }

    public TipoParentescoDTO buscarPorId(Long id) {
        Optional<TipoParentesco> tipoOpt = repository.findById(id);

        return tipoOpt.map(tipo -> new TipoParentescoDTO(
                tipo.getIdTipoParentesco(),
                tipo.getDescricaoParentesco()
        )).orElseThrow(() -> new RuntimeException("Tipo de Parentesco não encontrado para o ID " + id));
    }

    public List<TipoParentescoDTO> listarTodos() {
        List<TipoParentesco> tipos = repository.findAll();

        return tipos.stream()
                .map(tipo -> new TipoParentescoDTO(
                        tipo.getIdTipoParentesco(),
                        tipo.getDescricaoParentesco()))
                .toList();
    }
}
