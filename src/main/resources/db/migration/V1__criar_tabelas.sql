CREATE TABLE contribuinte (
  idContribuinte BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  nome_social VARCHAR(255),
  nome_civil VARCHAR(255),
  cpf VARCHAR(11) UNIQUE,
  ativo BOOLEAN DEFAULT true,
  idCategoria BIGINT NOT NULL
);

CREATE TABLE categoria (
  idCategoria BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  nomeCategoria VARCHAR(100),
  percentual_contribuicao DECIMAL(5,2)
);

CREATE TABLE historicoSalarioMinimo (
  idSalarioMinimo BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  valor DECIMAL(10,2) NOT NULL,
  data_inicio DATE NOT NULL,
  data_fim DATE
);

CREATE TABLE endereco (
  idEndereco BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  idContribuinte BIGINT NOT NULL,
  cep VARCHAR(8),
  numero VARCHAR(10),
  estado VARCHAR(50)
);

CREATE TABLE telefone (
  idTelefone BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  idContribuinte BIGINT NOT NULL,
  numero VARCHAR(15),
  tipo VARCHAR(20)
);

CREATE TABLE email (
  idEmail BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  idContribuinte BIGINT NOT NULL,
  email VARCHAR(255) UNIQUE
);

CREATE TABLE tipoParentesco (
  idTipoParentesco BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  descricao VARCHAR(50) UNIQUE
);

CREATE TABLE filiacao (
  idFiliacao BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  idContribuinteFilho BIGINT NOT NULL,
  nomeParente VARCHAR(255),
  cpfParente VARCHAR(11),
  idTipoParentesco BIGINT NOT NULL
);

CREATE TABLE contribuicao (
  idContribuicao BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  idContribuinte BIGINT NOT NULL,
  data_contribuicao TIMESTAMP,
  valor_contribuicao DECIMAL(10,2),
  mes_referente INT,
  ano_referente INT,
  idSalarioMinimo BIGINT NOT NULL
);

ALTER TABLE contribuinte ADD FOREIGN KEY (idCategoria) REFERENCES categoria(idCategoria);

ALTER TABLE endereco ADD FOREIGN KEY (idContribuinte) REFERENCES contribuinte(idContribuinte);

ALTER TABLE telefone ADD FOREIGN KEY (idContribuinte) REFERENCES contribuinte(idContribuinte);

ALTER TABLE email ADD FOREIGN KEY (idContribuinte) REFERENCES contribuinte(idContribuinte);

ALTER TABLE filiacao ADD FOREIGN KEY (idContribuinteFilho) REFERENCES contribuinte(idContribuinte);

ALTER TABLE filiacao ADD FOREIGN KEY (idTipoParentesco) REFERENCES tipoParentesco(idTipoParentesco);

ALTER TABLE contribuicao ADD FOREIGN KEY (idContribuinte) REFERENCES contribuinte(idContribuinte);

ALTER TABLE contribuicao ADD FOREIGN KEY (idSalarioMinimo) REFERENCES historicoSalarioMinimo(idSalarioMinimo);
