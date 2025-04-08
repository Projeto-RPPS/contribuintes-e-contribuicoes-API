package com.rpps.rppsProject.Repository;

import com.rpps.rppsProject.Model.Telefone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Repository
public class TelefoneRepository implements GenericRepository<Telefone, Void> {
    @Autowired
    private JdbcTemplate template;

    private final RowMapper<Telefone> rowMapper = new RowMapper<Telefone>() {
        @Override
        public Telefone mapRow(ResultSet rs, int rowNum) throws SQLException {
            Telefone telefone = new Telefone();
            telefone.setIdTelefone(rs.getLong("idtelefone"));
            telefone.setIdContribuinte(rs.getLong("idcontribuinte"));
            telefone.setNumero(rs.getString("numero"));
            telefone.setTipoTelefone(rs.getString("tipo"));
            return telefone;
        }
    };

    @Override
    public Void insert(Telefone entity) {
        try {
            String sql = "INSERT INTO telefone (idcontribuinte, numero, tipo) VALUES (?, ?, ?)";
            template.update(sql, entity.getIdContribuinte(), entity.getNumero(), entity.getTipoTelefone());
        } catch (DataAccessException e) {
            System.err.println("Erro ao inserir telefone: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Void update(Telefone entity) {
        try {
            String sql = "UPDATE telefone SET idcontribuinte = ?, numero = ?, tipo = ? WHERE idtelefone = ?";
            template.update(sql, entity.getIdContribuinte(), entity.getNumero(), entity.getTipoTelefone(), entity.getIdTelefone());
        } catch (DataAccessException e) {
            System.err.println("Erro ao atualizar telefone: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM telefone WHERE idtelefone = ?";
            template.update(sql, id);
        } catch (DataAccessException e) {
            System.err.println("Erro ao deletar telefone: " + e.getMessage());
        }
    }

    @Override
    public Optional<Telefone> findById(Long id) {
        try {
            String sql = "SELECT * FROM telefone WHERE idtelefone = ?";
            Telefone telefone = template.queryForObject(sql, rowMapper, id);
            return Optional.ofNullable(telefone);
        } catch (DataAccessException e) {
            System.err.println("Erro ao buscar telefone por ID: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Telefone> findAll() {
        try {
            String sql = "SELECT * FROM telefone";
            return template.query(sql, rowMapper);
        } catch (DataAccessException e) {
            System.err.println("Erro ao buscar todos os telefones: " + e.getMessage());
            return List.of();
        }
    }
}
