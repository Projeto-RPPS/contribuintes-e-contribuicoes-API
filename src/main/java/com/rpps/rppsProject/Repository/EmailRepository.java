package com.rpps.rppsProject.Repository;

import com.rpps.rppsProject.Model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class EmailRepository implements GenericRepository<Email, Void> {
    @Autowired
    private JdbcTemplate template;

    RowMapper<Email> rowMapper = new RowMapper<Email>() {
        @Override
        public Email mapRow(ResultSet rs, int rowNum) throws SQLException {
            Email email = new Email();
            email.setIdEmail(rs.getLong("idemail"));
            email.setIdContribuinte(rs.getLong("idcontribuinte"));
            email.setEmail(rs.getString("email"));
            return email;
        }
    };

    @Override
    public Void insert(Email entity) {
        try {
            String sql = "INSERT INTO email (idcontribuinte, email) VALUES (?, ?)";
            template.update(sql, entity.getIdContribuinte(), entity.getEmail());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir email", e);
        }
        return null;
    }

    @Override
    public Void update(Email entity) {
        try {
            String sql = "UPDATE email SET email = ? WHERE idemail = ?";
            template.update(sql, entity.getEmail(), entity.getIdEmail());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar email", e);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM email WHERE idemail = ?";
            template.update(sql, id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir email", e);
        }
    }

    @Override
    public Optional<Email> findById(Long id) {
        try {
            String sql = "SELECT * FROM email WHERE idemail = ?";
            Email email = template.queryForObject(sql, rowMapper, id);
            return Optional.ofNullable(email);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Email> findAll() {
        try {
            String sql = "SELECT * FROM email";
            return template.query(sql, rowMapper);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar lista de emails", e);
        }
    }
}
