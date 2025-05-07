package com.rpps.rppsProject.dto.contribuicao;

import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public record DadosFinanceirosCpfDTO(Long idContribuinte, BigDecimal valorSalario, Long idSalario, BigDecimal percentualContribuicao) {
    public static RowMapper<DadosFinanceirosCpfDTO> rowMapper = new RowMapper<DadosFinanceirosCpfDTO>() {
        @Override
        public DadosFinanceirosCpfDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            DadosFinanceirosCpfDTO dadosFinanceiros = new DadosFinanceirosCpfDTO(
                    rs.getLong("idcontribuinte"),
                    rs.getBigDecimal("salario_minimo"),
                    rs.getLong("idsalariominimo"),
                    rs.getBigDecimal("percentual_contribuicao")
            );

            return dadosFinanceiros;
        }
    };
}
