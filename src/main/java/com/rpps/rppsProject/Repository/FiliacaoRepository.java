package com.rpps.rppsProject.Repository;

import com.rpps.rppsProject.Model.Filiacao;
import com.rpps.rppsProject.DTOS.DadosGenealogicosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class FiliacaoRepository implements GenericRepository<Filiacao, Long> {
    @Autowired
    private JdbcTemplate template;

    RowMapper<Filiacao> rowMapper = new RowMapper<Filiacao>() {
        @Override
        public Filiacao mapRow(ResultSet rs, int rowNum) throws SQLException {
            Filiacao filiacao = new Filiacao();
            filiacao.setIdFiliacao(rs.getLong("idfiliacao"));
            filiacao.setIdContribuinte(rs.getLong("idcontribuinte"));
            filiacao.setIdTipoParentesco(rs.getLong("idtipoparentesco"));
            filiacao.setIdParente(rs.getLong("idparente"));
            return filiacao;
        }
    };

    @Override
    public Long insert(Filiacao entity) {
        try {
            String sql = "INSERT INTO filiacao (idcontribuinte, idtipoparentesco, idparente) VALUES (?, ?, ?)";
            template.update(sql, entity.getIdContribuinte(), entity.getIdTipoParentesco(), entity.getIdParente());
            return entity.getIdFiliacao();
        } catch (Exception e) {
            throw new RuntimeException("Erro no insert de Filiacao", e);
        }
    }

    @Override
    public Long update(Filiacao entity) {
        try {
            String sql = "UPDATE filiacao SET idcontribuinte = ?, idtipoparentesco = ?, idparente = ? WHERE idfiliacao = ?";
            template.update(sql, entity.getIdContribuinte(), entity.getIdTipoParentesco(), entity.getIdParente(), entity.getIdFiliacao());
            return entity.getIdFiliacao();
        } catch (Exception e) {
            throw new RuntimeException("Erro no update de Filiacao", e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM filiacao WHERE idfiliacao = ?";
            template.update(sql, id);
        } catch (Exception e) {
            throw new RuntimeException("Erro no delete de Filiacao", e);
        }
    }

    @Override
    public Optional<Filiacao> findById(Long id) {
        try {
            String sql = "SELECT * FROM filiacao WHERE idfiliacao = ?";
            return Optional.ofNullable(template.queryForObject(sql, rowMapper, id));
        } catch (Exception e) {
            throw new RuntimeException("Erro no findById de Filiacao", e);
        }
    }

    @Override
    public List<Filiacao> findAll() {
        try {
            String sql = "SELECT * FROM filiacao";
            return template.query(sql, rowMapper);
        } catch (Exception e) {
            throw new RuntimeException("Erro no findAll de Filiacao", e);
        }
    }

    public List<DadosGenealogicosDTO> obterDadosArvoreGenealogica(Long idContribuinte) {
        String sql = """
        SELECT p.nomeparente, t.descricao FROM filiacao f
        JOIN tipoparentesco t ON f.idtipoparentesco = t.idtipoparentesco
        JOIN parente p ON f.idparente = p.idparente
        WHERE idcontribuinte = ?
        """;

        return template.query(sql, DadosGenealogicosDTO.rowMapper, idContribuinte);
    }
}
