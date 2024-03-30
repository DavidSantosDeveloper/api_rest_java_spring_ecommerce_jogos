CREATE TABLE cargo (
  cod_cargo BIGINT  NOT NULL GENERATED ALWAYS AS IDENTITY,
  salario real  not NULL,
  descricao text,
  CONSTRAINT PK_cargo PRIMARY KEY(cod_cargo)
);