CREATE TABLE funcionario (
  cod_funcionario BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
  nome varchar(255) not NULL,
  telefone varchar(20),
  dt_nasc Date,
  cpf varchar(20) not null,
  cep varchar(255),
  pais varchar(255),
  Estado varchar(255),
  cidade varchar(255),
  bairro varchar(255),
  logradouro varchar(255),
  numero varchar(255),
  ponto_de_referencia varchar(255),
  cod_cargo BigInt not null references cargo(cod_cargo),
  CONSTRAINT PK_FUNCIONARIO PRIMARY KEY(cod_funcionario)
);