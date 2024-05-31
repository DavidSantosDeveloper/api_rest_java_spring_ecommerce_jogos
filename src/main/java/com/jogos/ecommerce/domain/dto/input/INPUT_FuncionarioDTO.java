package com.jogos.ecommerce.domain.dto.input;

import java.sql.Date;

import com.jogos.ecommerce.domain.model.Cargo;

public record INPUT_FuncionarioDTO
(
  String nome,
  String telefone,
Date dt_nasc,
 String cpf,
 double salario,
 String cep,
 String pais, 
 String estado,
String cidade,
 String  bairro,
String logradouro,
String numero,
String ponto_de_referencia,
Cargo cargo
)
{

}
