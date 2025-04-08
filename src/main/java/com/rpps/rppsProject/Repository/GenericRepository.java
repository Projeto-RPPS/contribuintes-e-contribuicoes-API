package com.rpps.rppsProject.Repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository <T, R> {

    // Método para inserir
    R insert(T entity);

    // Método para atualizar
    R update(T entity);

    // Método para excluir
    void delete(Long id);

    // Método para buscar por ID
    Optional<T> findById(Long id);

    // Método para listar todos os registros
    List<T> findAll();
}
