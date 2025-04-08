package com.rpps.rppsProject.Repository;

import com.rpps.rppsProject.Model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaRepository implements GenericRepository<Categoria, Void> {
    @Autowired
    JdbcTemplate template;

    private final RowMapper<Categoria> rowMapper = new RowMapper<Categoria>() {
        @Override
        public Categoria mapRow(ResultSet rs, int rowNum) throws SQLException {
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(rs.getLong("idcategoria"));
            categoria.setNomeCategoria(rs.getString("nomecategoria"));
            categoria.setPercentualContribuicao(rs.getBigDecimal("percentual_contribuicao"));
            System.out.println(categoria.getPercentualContribuicao());

            return categoria;
        }
    };

    @Override
    public Void insert(Categoria entity) {
        try {
            String sql = "INSERT INTO categoria (nomecategoria, percentual_contribuicao) VALUES(?, ?);";
            template.update(sql, entity.getNomeCategoria(), entity.getPercentualContribuicao());
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Erro no insert de Categoria", e);
        }
    }

    @Override
    public Void update(Categoria entity) {
        String sql = "UPDATE categoria SET percentual_contribuicao = ?, nomecategoria = ? WHERE idcategoria = ?;";

        try {
            template.update(sql,
                    entity.getPercentualContribuicao(),
                    entity.getNomeCategoria(),
                    entity.getIdCategoria()
            );
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Erro no update de Categoria", e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "SELECT COUNT(*) FROM contribuinte WHERE idCategoria = ?;";
            Integer count = template.queryForObject(sql, Integer.class, id);
            if (count != null && count == 0) {
                sql = "DELETE FROM categoria where idcategoria = ?;";
                template.update(sql, id);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro no delete da categoria", e);
        }

    }

    @Override
    public Optional<Categoria> findById(Long id) {
        try {
            String sql = "SELECT * FROM categoria WHERE idcategoria = ?;";
            Categoria categoria = template.queryForObject(sql, rowMapper, id);
            return Optional.ofNullable(categoria);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Categoria> findAll() {
        try {
            String sql = "SELECT * FROM categoria;";
            return template.query(sql, rowMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro no findAll de Categoria", e);
        }
    }
}
