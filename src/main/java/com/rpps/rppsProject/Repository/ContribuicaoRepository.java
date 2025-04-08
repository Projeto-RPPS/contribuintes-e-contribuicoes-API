package com.rpps.rppsProject.Repository;

import com.rpps.rppsProject.Model.Contribuicao;
import com.rpps.rppsProject.DTOS.DadosFinanceirosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class ContribuicaoRepository implements GenericRepository<Contribuicao, Long> {
    @Autowired
    private JdbcTemplate template;

    private final RowMapper<Contribuicao> rowMapper = (rs, rowNum) -> {
        Contribuicao contribuicao = new Contribuicao();
        contribuicao.setIdContribuicao(rs.getLong("idcontribuicao"));
        contribuicao.setIdContribuinte(rs.getLong("idcontribuinte"));
        contribuicao.setDataContribuicao(rs.getDate("data_contribuicao").toLocalDate());
        contribuicao.setValorContribuicao(rs.getBigDecimal("valor_contribuicao"));
        contribuicao.setDataReferente(rs.getDate("data_referencia").toLocalDate());
        contribuicao.setIdSalarioMinimo(rs.getLong("idsalariominimo"));
        return contribuicao;
    };

    public DadosFinanceirosDTO buscarPercentualESalario(LocalDate dataReferencia, Long idContribuinte) {
        try {
            String sql = """
                SELECT c.percentual_contribuicao, 
                       h.idsalariominimo, 
                       h.valor AS salario_minimo
                FROM contribuinte co
                JOIN categoria c ON co.idcategoria = c.idcategoria
                JOIN historicosalariominimo h 
                    ON h.data_inicio <= ?  
                    AND (h.data_fim IS NULL OR h.data_fim >= ?)
                WHERE co.idcontribuinte = ?
                ORDER BY h.data_inicio DESC
                LIMIT 1
            """;
            return template.queryForObject(sql, DadosFinanceirosDTO.rowMapper, dataReferencia, dataReferencia, idContribuinte);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar percentual e salario minimo", e);
        }
    }

    @Override
    public Long insert(Contribuicao entity) {
        try {
            String sql = """
                INSERT INTO contribuicao (idcontribuinte, data_contribuicao, valor_contribuicao, 
                                          data_referencia, idsalariominimo) 
                VALUES (?, ?, ?, ?, ?) 
                RETURNING idcontribuicao
            """;
            entity.setDataContribuicao(LocalDate.now());

            return template.queryForObject(sql, Long.class,
                    entity.getIdContribuinte(),
                    entity.getDataContribuicao(),
                    entity.getValorContribuicao(),
                    entity.getDataReferente(),
                    entity.getIdSalarioMinimo()
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro no método Insert de Contribuicao", e);
        }
    }

    @Override
    public Long update(Contribuicao entity) {
        try {
            String sql = """
                UPDATE contribuicao 
                SET idcontribuinte = ?, data_contribuicao = ?, data_referencia = ?, 
                    idsalariominimo = ?, valor_contribuicao = ?
                WHERE idcontribuicao = ?
            """;

            int rowsUpdated = template.update(sql,
                    entity.getIdContribuinte(),
                    entity.getDataContribuicao(),
                    entity.getDataReferente(),
                    entity.getIdSalarioMinimo(),
                    entity.getValorContribuicao(),
                    entity.getIdContribuicao()
            );

            return rowsUpdated > 0 ? entity.getIdContribuicao() : null;
        } catch (Exception e) {
            throw new RuntimeException("Erro no método Update de Contribuicao", e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM contribuicao WHERE idcontribuicao = ?";
            template.update(sql, id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar contribuição", e);
        }
    }

    @Override
    public Optional<Contribuicao> findById(Long id) {
        try {
            String sql = "SELECT * FROM contribuicao WHERE idcontribuicao = ?";
            Contribuicao contribuicao = template.queryForObject(sql, rowMapper, id);
            return Optional.ofNullable(contribuicao);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Contribuicao> findAll() {
        try {
            String sql = "SELECT * FROM contribuicao";
            return template.query(sql, rowMapper);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todas as contribuições", e);
        }
    }

    public List<Contribuicao> findAllContribuicaoPorCPF(String cpfContribuinte) {
        try {
            String sql = """
            SELECT contribuicao.* FROM contribuicao
            JOIN contribuinte c ON c.idcontribuinte = contribuicao.idcontribuinte
            WHERE c.cpf = ?;
            """;
            return template.query(sql, rowMapper, cpfContribuinte);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar contribuições pelo CPF", e);
        }
    }

    public boolean existeContribuicaoNoMesmoMes(Long idContribuinte, LocalDate dataReferencia) {
        String sql = """
        SELECT COUNT(*) 
        FROM contribuicao 
        WHERE idcontribuinte = ? 
        AND EXTRACT(YEAR FROM data_referencia) = ?
        AND EXTRACT(MONTH FROM data_referencia) = ?
    """;

        Integer count = template.queryForObject(sql, Integer.class,
                idContribuinte,
                dataReferencia.getYear(),
                dataReferencia.getMonthValue()
        );

        Boolean existe = count != null && count > 0;

        return existe;
    }

}
