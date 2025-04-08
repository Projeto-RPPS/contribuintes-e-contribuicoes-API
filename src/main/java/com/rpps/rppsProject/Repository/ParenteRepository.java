package com.rpps.rppsProject.Repository;

import com.rpps.rppsProject.Model.Parente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ParenteRepository implements GenericRepository<Parente, Long> {
    @Autowired
    private JdbcTemplate template;

    RowMapper<Parente> rowMapper = new RowMapper<Parente>() {
        @Override
        public Parente mapRow(ResultSet rs, int rowNum) throws SQLException {
            Parente parente = new Parente();
            parente.setIdParente(rs.getLong("idparente"));
            parente.setNomeParente(rs.getString("nomeparente"));
            parente.setCpfParente(rs.getString("cpfparente"));

            return parente;
        }
    };

    @Override
    public Long insert(Parente entity) {
        try {
            String sql = "SELECT idparente FROM parente WHERE cpfparente = ?";
            List<Long> ids = template.query(sql, (rs, rowNum) -> rs.getLong("idparente"), entity.getCpfParente());

            Optional<Long> idOptional = ids.stream().findFirst();

            if (idOptional.isPresent()) {
                return idOptional.get();
            } else {
                String sql2 = "INSERT INTO parente (nomeparente, cpfparente) VALUES (?, ?) RETURNING idparente;";
                return template.queryForObject(sql2, Long.class, entity.getNomeParente(), entity.getCpfParente());
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro no insert de Parente", e);
        }
    }


    @Override
    public Long update(Parente entity) {
        try {
            String sql = "UPDATE parente SET nomeparente = ?, cpfparente = ? WHERE idparente = ?";
            template.update(sql, entity.getNomeParente(), entity.getCpfParente(), entity.getIdParente());
            return entity.getIdParente();
        } catch (Exception e) {
            throw new RuntimeException("Erro no update de Parente", e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM parente WHERE idparente = ?";
            template.update(sql, id);
        } catch (Exception e) {
            throw new RuntimeException("Erro no delete de Parente", e);
        }
    }

    @Override
    public Optional<Parente> findById(Long id) {
        try {
            String sql = "SELECT * FROM parente WHERE idparente = ?";
            return Optional.ofNullable(template.queryForObject(sql, rowMapper, id));
        } catch (Exception e) {
            throw new RuntimeException("Erro no findById de Parente", e);
        }
    }

    @Override
    public List<Parente> findAll() {
        try {
            String sql = "SELECT * FROM parente";
            return template.query(sql, rowMapper);
        } catch (Exception e) {
            throw new RuntimeException("Erro no findAll de Parente", e);
        }
    }
}
