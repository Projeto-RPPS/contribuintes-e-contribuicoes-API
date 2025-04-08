package com.rpps.rppsProject.DTOS;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public record DadosGenealogicosDTO(String nomeParente,
                                   String descricaoParentesco) {
    public static RowMapper<DadosGenealogicosDTO> rowMapper = new RowMapper<DadosGenealogicosDTO>() {
        @Override
        public DadosGenealogicosDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new DadosGenealogicosDTO(rs.getString("nomeparente"),
                    rs.getString("descricao"));
        }
    };
}
