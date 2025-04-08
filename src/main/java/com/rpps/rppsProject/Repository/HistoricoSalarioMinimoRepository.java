package com.rpps.rppsProject.Repository;

import com.rpps.rppsProject.Model.HistoricoSalarioMinimo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class HistoricoSalarioMinimoRepository implements GenericRepository<HistoricoSalarioMinimo, Long> {
    @Autowired
    private JdbcTemplate template;

    RowMapper<HistoricoSalarioMinimo> rowMapper = new RowMapper<HistoricoSalarioMinimo>() {
        @Override
        public HistoricoSalarioMinimo mapRow(ResultSet rs, int rowNum) throws SQLException {
            HistoricoSalarioMinimo historicoSalarioMinimo = new HistoricoSalarioMinimo();
            historicoSalarioMinimo.setIdSalarioMinimo(rs.getLong("idsalariominimo"));
            historicoSalarioMinimo.setValor(rs.getBigDecimal("valor"));
            historicoSalarioMinimo.setDataInicioVigencia(rs.getDate("data_inicio").toLocalDate());
            Date dataFim = rs.getDate("data_fim");
            if (dataFim != null) {
                historicoSalarioMinimo.setDataFimVigencia(dataFim.toLocalDate());
            }
            return historicoSalarioMinimo;
        }
    };

    @Override
    public Long insert(HistoricoSalarioMinimo entity) {
        String sql = "INSERT INTO historicosalariominimo (valor, data_inicio, data_fim) VALUES (?, ?, ?) RETURNING idsalariominimo";
        return template.queryForObject(sql, Long.class,
                entity.getValor(),
                entity.getDataInicioVigencia(),
                entity.getDataFimVigencia()
        );
    }

    @Override
    public Long update(HistoricoSalarioMinimo entity) {
        String sql = "UPDATE historicosalariominimo SET valor = ?, data_inicio = ?, data_fim = ? WHERE idsalariominimo = ?";
        int rows = template.update(sql,
                entity.getValor(),
                entity.getDataInicioVigencia(),
                entity.getDataFimVigencia(),
                entity.getIdSalarioMinimo()
        );
        return rows > 0 ? entity.getIdSalarioMinimo() : null;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM historicosalariominimo WHERE idsalariominimo = ?";
        template.update(sql, id);
    }

    @Override
    public Optional<HistoricoSalarioMinimo> findById(Long id) {
        String sql = "SELECT * FROM historicosalariominimo WHERE idsalariominimo = ?";
        List<HistoricoSalarioMinimo> result = template.query(sql, rowMapper, id);
        return result.stream().findFirst();
    }

    @Override
    public List<HistoricoSalarioMinimo> findAll() {
        String sql = "SELECT * FROM historicosalariominimo";
        return template.query(sql, rowMapper);
    }
}
