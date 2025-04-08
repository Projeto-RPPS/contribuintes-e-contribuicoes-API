package com.rpps.rppsProject.Repository;

import com.rpps.rppsProject.Model.TipoParentesco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class TipoParentescoRepository implements GenericRepository<TipoParentesco, Void> {
    @Autowired
    private JdbcTemplate template;

    RowMapper<TipoParentesco> rowMapper = new RowMapper<TipoParentesco>() {
        @Override
        public TipoParentesco mapRow(ResultSet rs, int rowNum) throws SQLException {
            TipoParentesco tipoParentesco = new TipoParentesco();
            tipoParentesco.setIdTipoParentesco(rs.getLong("idtipoparentesco"));
            tipoParentesco.setDescricaoParentesco(rs.getString("descricao"));
            return tipoParentesco;
        }
    };

    @Override
    public Void insert(TipoParentesco entity) {
        try {
            String sql = "INSERT INTO tipoparentesco (descricao) VALUES (?)";
            template.update(sql, entity.getDescricaoParentesco());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir TipoParentesco", e);
        }
        return null;
    }

    @Override
    public Void update(TipoParentesco entity) {
        try {
            String sql = "UPDATE tipoparentesco SET descricao = ? WHERE idtipoparentesco = ?";
            template.update(sql, entity.getDescricaoParentesco(), entity.getIdTipoParentesco());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar TipoParentesco", e);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM tipoparentesco WHERE idtipoparentesco = ?";
            template.update(sql, id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar TipoParentesco", e);
        }
    }

    @Override
    public Optional<TipoParentesco> findById(Long id) {
        try {
            String sql = "SELECT * FROM tipoparentesco WHERE idtipoparentesco = ?";
            return Optional.ofNullable(template.queryForObject(sql, rowMapper, id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<TipoParentesco> findAll() {
        try {
            String sql = "SELECT * FROM tipoparentesco";
            return template.query(sql, rowMapper);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todos os TipoParentesco", e);
        }
    }
}
