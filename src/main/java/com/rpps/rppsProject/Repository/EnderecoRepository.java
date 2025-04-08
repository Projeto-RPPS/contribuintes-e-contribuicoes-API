package com.rpps.rppsProject.Repository;

import com.rpps.rppsProject.Model.Endereco;
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
public class EnderecoRepository implements GenericRepository<Endereco, Void> {
    @Autowired
    private JdbcTemplate template;

    private final RowMapper<Endereco> rowMapper = new RowMapper<Endereco>() {
        @Override
        public Endereco mapRow(ResultSet rs, int rowNum) throws SQLException {
            Endereco endereco = new Endereco();
            endereco.setIdEndereco(rs.getLong("idendereco"));
            endereco.setIdContribuinte(rs.getLong("idcontribuinte"));
            endereco.setCep(rs.getString("cep"));
            endereco.setNumero(rs.getString("numero"));
            endereco.setEstado(rs.getString("estado"));
            return endereco;
        }
    };

    @Override
    public Void insert(Endereco entity) {
        try {
            String sql = "INSERT INTO endereco (idcontribuinte, cep, numero, estado) VALUES (?, ?, ?, ?)";
            template.update(sql, entity.getIdContribuinte(), entity.getCep(), entity.getNumero(), entity.getEstado());
        } catch (DataAccessException e) {
            System.err.println("Erro ao inserir endereco: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Void update(Endereco entity) {
        try {
            String sql = "UPDATE endereco SET idcontribuinte = ?, cep = ?, numero = ?, estado = ? WHERE idendereco = ?";
            template.update(sql, entity.getIdContribuinte(), entity.getCep(), entity.getNumero(), entity.getEstado(), entity.getIdEndereco());
        } catch (DataAccessException e) {
            System.err.println("Erro ao atualizar endereco: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM endereco WHERE idendereco = ?";
            template.update(sql, id);
        } catch (DataAccessException e) {
            System.err.println("Erro ao deletar endereco: " + e.getMessage());
        }
    }

    @Override
    public Optional<Endereco> findById(Long id) {
        try {
            String sql = "SELECT * FROM endereco WHERE idendereco = ?";
            Endereco endereco = template.queryForObject(sql, rowMapper, id);
            return Optional.ofNullable(endereco);
        } catch (DataAccessException e) {
            System.err.println("Erro ao buscar endereco por ID: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Endereco> findAll() {
        try {
            String sql = "SELECT * FROM endereco";
            return template.query(sql, rowMapper);
        } catch (DataAccessException e) {
            System.err.println("Erro ao buscar todos os enderecos: " + e.getMessage());
            return List.of();
        }
    }
}