package com.rpps.rppsProject.DTOS;

import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public record DadosFinanceirosDTO(BigDecimal valorSalario, Long idSalario, BigDecimal percentualContribuicao) {
    public static RowMapper<DadosFinanceirosDTO> rowMapper = new RowMapper<DadosFinanceirosDTO>() {
        @Override
        public DadosFinanceirosDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            DadosFinanceirosDTO dadosFinanceiros = new DadosFinanceirosDTO(
                    rs.getBigDecimal("salario_minimo"),
                    rs.getLong("idsalariominimo"),
                    rs.getBigDecimal("percentual_contribuicao")
            );

            return dadosFinanceiros;
        }
    };
}
