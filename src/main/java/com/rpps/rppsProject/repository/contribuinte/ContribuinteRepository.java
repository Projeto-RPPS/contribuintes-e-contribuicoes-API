package com.rpps.rppsProject.repository.contribuinte;

import com.rpps.rppsProject.model.contribuinte.Contribuinte;
import com.rpps.rppsProject.repository.GenericRepository;
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
public class ContribuinteRepository implements GenericRepository<Contribuinte, Long> {
    @Autowired
    private JdbcTemplate template;

    private final RowMapper<Contribuinte> rowMapper = new RowMapper<Contribuinte>() {
        @Override
        public Contribuinte mapRow(ResultSet rs, int rowNum) throws SQLException {
            Contribuinte contribuinte = new Contribuinte();
            contribuinte.setIdContribuinte(rs.getLong("idcontribuinte"));
            contribuinte.setNomeSocial(rs.getString("nome_social"));
            contribuinte.setNomeCivil(rs.getString("nome_civil"));
            contribuinte.setCpf(rs.getString("cpf"));
            contribuinte.setIsAtivo(rs.getBoolean("ativo"));
            contribuinte.setIdCategoria(rs.getLong("idcategoria"));
            return contribuinte;
        }
    };

    @Override
    public Long insert(Contribuinte entity) {
        String sql = "INSERT INTO contribuinte (nome_social, nome_civil, ativo, cpf, idcategoria) values (?, ?, ?, ?, ?) RETURNING idcontribuinte;";
        try {
            Long id = template.queryForObject(sql, Long.class,
                    entity.getNomeSocial(),
                    entity.getNomeCivil(),
                    entity.getIsAtivo(),
                    entity.getCpf(),
                    entity.getIdCategoria());

            return id;
        } catch (Exception e) {
            throw new RuntimeException("Erro insert Contribuinte", e);
        }
    }

    @Override
    public Long update(Contribuinte entity) {
        String sql = "UPDATE contribuinte SET nome_social = ?, nome_civil = ?, ativo = ?, cpf = ?, idcategoria = ? WHERE idcontribuinte = ?;";

        try {
            int rowsAffected = template.update(sql,
                    entity.getNomeSocial(),
                    entity.getNomeCivil(),
                    entity.getIsAtivo(),
                    entity.getCpf(),
                    entity.getIdCategoria(),
                    entity.getIdContribuinte()
            );

            if (rowsAffected > 0) {
                return entity.getIdContribuinte();
            } else {
                throw new RuntimeException("Contribuinte não encontrado para o ID " + entity.getIdContribuinte());
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar contribuinte", e);
        }
    }

    @Override
    public void delete(Long id) {
        try {

            String sql = "UPDATE contribuinte SET ativo = ? WHERE idcontribuinte = ?;";
            template.update(sql, false, id);

        } catch (DataAccessException e) {
            throw new RuntimeException("Erro no Delete de Contribuinte", e);
        }
    }

    public void deleteByCpf(String cpf) {
        try {

            String sql = "UPDATE contribuinte SET ativo = ? WHERE cpf = ?;";
            template.update(sql, false, cpf);

        } catch (DataAccessException e) {
            throw new RuntimeException("Erro no Delete de Contribuinte", e);
        }
    }

    @Override
    public Optional<Contribuinte> findById(Long id) {
        try {
            String sql = "SELECT * FROM contribuinte WHERE idcontribuinte = ?;";
            Contribuinte contribuinte = template.queryForObject(sql, rowMapper, id);
            return Optional.ofNullable(contribuinte);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<Contribuinte> findByCpf(String cpf) {
        try {
            String sql = "SELECT * FROM contribuinte WHERE cpf = ?;";
            Contribuinte contribuinte = template.queryForObject(sql, rowMapper, cpf);
            return Optional.ofNullable(contribuinte);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Contribuinte> findAll() {
        try {
            String sql = "SELECT * FROM contribuinte;";
            List<Contribuinte> listaContribuintes = template.query(sql, rowMapper);
            return listaContribuintes;
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro findAll de Contribuinte", e);
        }
    }

    public Boolean isAtivo(Long idContribuinte){
        try {
            String sql = "SELECT ativo FROM contribuinte where idcontribuinte = ?";
            return Boolean.TRUE.equals(template.queryForObject(sql, Boolean.class, idContribuinte));
        } catch (DataAccessException e) {
            throw new RuntimeException("Contribuinte não encontrado", e);
        }
    }

    public Boolean isAtivoPorCpf(String cpf){
        try {
            String sql = "SELECT ativo FROM contribuinte where cpf = ?";
            return Boolean.TRUE.equals(template.queryForObject(sql, Boolean.class, cpf));
        } catch (DataAccessException e) {
            throw new RuntimeException("Contribuinte não encontrado", e);
        }
    }

}
