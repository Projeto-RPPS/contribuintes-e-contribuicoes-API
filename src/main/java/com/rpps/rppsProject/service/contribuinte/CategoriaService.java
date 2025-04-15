package com.rpps.rppsProject.service.contribuinte;

import com.rpps.rppsProject.model.contribuinte.Categoria;
import com.rpps.rppsProject.repository.contribuinte.CategoriaRepository;
import com.rpps.rppsProject.dto.contribuinte.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void atualizarCategoria(Categoria categoria) {
        if (categoriaRepository.findById(categoria.getIdCategoria()).isEmpty()) {
            throw new RuntimeException("Categoria não encontrada para o ID");
        }
        categoriaRepository.update(categoria);
    }

    public void deletarCategoria(Long id) {
        categoriaRepository.delete(id);
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada com o id: " + id));
    }

    public List<Categoria> buscarTodas() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias;
    }
}
