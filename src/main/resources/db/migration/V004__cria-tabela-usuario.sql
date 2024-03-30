CREATE TABLE usuario (
  cod_usuario BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
  email VARCHAR(255) NOT NULL UNIQUE,
  senha varchar(255) NOT NULL,
  url_foto_perfil TEXT, 
  cod_cliente BIGINT,
  cod_funcionario BIGINT,
  CONSTRAINT Pk_USUARIO PRIMARY KEY(cod_usuario),
  CONSTRAINT FK_cliente_USUARIO FOREIGN KEY(cod_cliente) REFERENCES cliente(cod_cliente),
  CONSTRAINT FK_cod_funcionario_USUARIO FOREIGN KEY(cod_funcionario) REFERENCES funcionario(cod_funcionario)	
);