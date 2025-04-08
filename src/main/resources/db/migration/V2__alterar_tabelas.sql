-- Criando a nova tabela 'parente'
CREATE TABLE parente (
    idParente BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nomeParente VARCHAR(255) NOT NULL,
    cpfParente VARCHAR(11) UNIQUE
);

ALTER TABLE filiacao
    RENAME COLUMN idcontribuintefilho TO idcontribuinte;

-- Alterando a tabela 'filiacao'
ALTER TABLE filiacao
    DROP COLUMN nomeparente,
    DROP COLUMN cpfparente,
    ADD COLUMN idparente BIGINT NOT NULL,
    ADD CONSTRAINT fk_filiacao_parente FOREIGN KEY (idparente) REFERENCES parente(idparente);
