package com.rpps.rppsProject.Service;

import com.rpps.rppsProject.Model.Categoria;
import com.rpps.rppsProject.Repository.CategoriaRepository;
import com.rpps.rppsProject.DTOS.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    public void criarCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setNomeCategoria(categoriaDTO.nomeCategoria());
        categoria.setPercentualContribuicaoAntesDeSalvar(categoriaDTO.percentualContribuicao());

        categoriaRepository.insert(categoria);
    }

    public void atualizarCategoria(CategoriaDTO categoriaDTO) {
        if (categoriaRepository.findById(categoriaDTO.idCategoria()).isEmpty()) {
            throw new RuntimeException("Categoria não encontrada para o ID: " + categoriaDTO.idCategoria());
        }
        Categoria categoria = new Categoria();

        categoria.setIdCategoria(categoriaDTO.idCategoria());
        categoria.setNomeCategoria(categoriaDTO.nomeCategoria());
        categoria.setPercentualContribuicao(categoriaDTO.percentualContribuicao());

        categoriaRepository.update(categoria);
    }

    public void deletarCategoria(Long id) {
        categoriaRepository.delete(id);
    }

    public CategoriaDTO buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .map(categoria -> new CategoriaDTO(categoria.getIdCategoria(), categoria.getNomeCategoria(), categoria.getPercentualContribuicao()))
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o id: " + id));
    }

    public List<CategoriaDTO> buscarTodas() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoria -> new CategoriaDTO(categoria.getIdCategoria(), categoria.getNomeCategoria(), categoria.getPercentualContribuicao()))
                .toList();
    }
}
